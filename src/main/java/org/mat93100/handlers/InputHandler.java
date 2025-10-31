package org.mat93100.handlers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.mat93100.util.guiConstructor;

public class InputHandler implements Listener {

    private static final String guiPrefix = ChatColor.DARK_PURPLE + "Ranker - ";

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory() == null) return;

        String title = event.getView().getTitle();
        if (title == null || !title.contains("Online players")) return;

        event.setCancelled(true);

        ItemStack clicked = event.getCurrentItem();
        if (clicked == null || clicked.getType() != Material.PLAYER_HEAD) return;
        if (!clicked.hasItemMeta() || !clicked.getItemMeta().hasDisplayName()) return;

        Player clicker = (Player) event.getWhoClicked();
        String displayName = ChatColor.stripColor(clicked.getItemMeta().getDisplayName()).trim();

        if (!displayName.startsWith("Open: ")) return;
        String targetName = displayName.substring("Open: ".length()).trim();

        Player target = Bukkit.getPlayerExact(targetName);
        if (target != null && target.isOnline()) {
            guiConstructor.openPlayerGui(clicker, target);
        } else {
            clicker.sendMessage(ChatColor.RED + "The player has gone offline. Please re-run the command via /rank!");
            event.getClickedInventory().close();
        }
    }



    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        if (event.getView() == null || event.getView().getTitle() == null) return;
        String title = event.getView().getTitle();
        if (!title.startsWith(guiPrefix)) return;

        event.setCancelled(true);
        ((Player) event.getWhoClicked()).updateInventory();
    }

    @EventHandler
    public void onInventoryMoveItem(InventoryMoveItemEvent event) {
        Inventory source = event.getSource();
        if (source.getViewers().stream()
                .anyMatch(viewer -> viewer.getOpenInventory().getTitle().startsWith(guiPrefix))) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryPickupItem(InventoryPickupItemEvent event) {
        if (event.getInventory().getViewers().stream()
                .anyMatch(viewer -> viewer.getOpenInventory().getTitle().startsWith(guiPrefix))) {
            event.setCancelled(true);
        }
    }
}
