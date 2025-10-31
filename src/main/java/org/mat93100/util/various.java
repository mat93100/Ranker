package org.mat93100.util;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class various {
    public static String emptyOrNA(String value) {
        return (value == null || value.trim().isEmpty()) ? "N/A" : value;
    }

    public static String listOrNA(java.util.List<String> list) {
        return (list == null || list.isEmpty()) ? "N/A" : String.join(", ", list);
    }

    public static ItemStack getSkull(Player p, String displayName) {
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        skullMeta.setOwningPlayer(p);
        skullMeta.setDisplayName(displayName);
        skull.setItemMeta(skullMeta);
        return skull;
    }

}
