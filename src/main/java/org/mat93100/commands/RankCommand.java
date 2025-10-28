package org.mat93100.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.mat93100.main;

public class RankCommand implements CommandExecutor {

    private final main plugin;

    public RankCommand(main plugin) {
        this.plugin = plugin;//
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {

        if (!(sender instanceof Player player)) {
            if (args.length == 0 || args[0].equalsIgnoreCase("info")) {
                showInfo(sender, true);
                return true;
            }
            sender.sendMessage(ChatColor.RED + "The only argument available in the console is 'INFO'. Please use any other argument in game.");
            return true;
        }

        String usage = "Incorrect usage! Use /rank [Info|GUI|{playerName}]";

        if (args.length == 0) {
            player.sendMessage(ChatColor.RED + usage);
            return true;
        }

        if (args[0].equalsIgnoreCase("info")) {
            showInfo(player, false);
            return true;
        }

        if (args[0].equalsIgnoreCase("gui")) {
            openRankGUI(player);
            return true;
        }

        player.sendMessage(ChatColor.RED + usage);
        return true;
    }

    private void showInfo(CommandSender sender, boolean console) {
        String version = plugin.getDescription().getVersion();
        String name = plugin.getDescription().getName();
        String apiV = plugin.getDescription().getAPIVersion();
        String depend = String.join(", ", plugin.getDescription().getDepend());
        String authors = String.join(", ", plugin.getDescription().getAuthors());

        sender.sendMessage(ChatColor.LIGHT_PURPLE + "=== Plugin Information ===");
        sender.sendMessage(ChatColor.YELLOW + "Name: " + ChatColor.WHITE + name);
        sender.sendMessage(ChatColor.YELLOW + "Version: " + ChatColor.WHITE + version);
        sender.sendMessage(ChatColor.YELLOW + "Authors: " + ChatColor.WHITE + authors);

        if (console) {
            sender.sendMessage(ChatColor.GREEN + "Console additional info:");
            sender.sendMessage(ChatColor.YELLOW + "API Version: " + ChatColor.WHITE + apiV);
            sender.sendMessage(ChatColor.YELLOW + "Dependencies: " + ChatColor.WHITE + depend);
        }
    }

    private void openRankGUI(Player player) {
        Inventory inv = Bukkit.createInventory(null, 54, ChatColor.DARK_PURPLE + "Rank Menu");

        ItemStack glass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta meta = glass.getItemMeta();
        meta.setDisplayName(" ");
        glass.setItemMeta(meta);

        for (int i = 0; i < inv.getSize(); i++) {
            inv.setItem(i, glass);
        }
        player.openInventory(inv);
    }
}
