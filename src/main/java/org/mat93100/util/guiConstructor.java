package org.mat93100.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.mat93100.util.various.getSkull;

public class guiConstructor {

    private static final String guiPrefix = ChatColor.DARK_PURPLE + "Ranker - ";

    public static void openAllPlayerGui(Player viewer) {

        Inventory inv = Bukkit.createInventory(null, 54, guiPrefix + "Online players");

        fillGlass(inv);

        List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
        players.sort(Comparator.comparing(Player::getName, String.CASE_INSENSITIVE_ORDER));

        int index = 0;
        for (Player p : players) {
            if (index >= inv.getSize()) break;

            ItemStack skull = getSkull(p, ChatColor.DARK_GREEN + "Open: " + ChatColor.GREEN + p.getName());
            inv.setItem(index, skull);
            index++;
        }
        viewer.openInventory(inv);
    }

    public static void openPlayerGui(Player viewer, Player target) {
        Inventory inv = Bukkit.createInventory(null, 54, guiPrefix + "Online players");

        fillGlass(inv);

        List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
        players.sort(Comparator.comparing(Player::getName, String.CASE_INSENSITIVE_ORDER));


        ItemStack skull = getSkull(target, ChatColor.GREEN + target.getName());
        inv.setItem(13, skull);


        viewer.openInventory(inv);
    }

    public static void openRankGui(Player viewer) {
        //TODO
    }

    private static void fillGlass(@NotNull Inventory inv) {
        ItemStack glass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta meta = glass.getItemMeta();
        meta.setDisplayName(" ");
        glass.setItemMeta(meta);

        for (int i = 0; i < inv.getSize(); i++) {
            if (inv.getItem(i) == null || inv.getItem(i).getType() == Material.AIR) {
                inv.setItem(i, glass);
            }
        }
    }

}
