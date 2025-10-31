package org.mat93100.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.mat93100.main;
import org.mat93100.util.guiConstructor;
import org.mat93100.util.various;

public class RankCommand implements CommandExecutor {

    private final main plugin;

    public RankCommand(main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) {
            if (args.length > 0 && args[0].equalsIgnoreCase("info")) {
                showInfo(sender, true);
            } else {
                sender.sendMessage(ChatColor.RED + "The only argument available in the console is 'INFO'.");
            }
            return true;
        }

        if (args.length == 0 || args[0].equalsIgnoreCase("gui")) {
            guiConstructor.openAllPlayerGui(player);
            return true;
        }

        if (args[0].equalsIgnoreCase("info")) {
            showInfo(player, false);
            return true;
        }

        Player target = Bukkit.getPlayerExact(args[0]);
        if (target != null && target.isOnline()) {
            guiConstructor.openPlayerGui(player, target);
            return true;
        }

        player.sendMessage(ChatColor.RED + "Invalid argument! Use /rank [{empty} | gui | info | {onlinePlayerName} : Make sure player is online!]");
        return true;
    }

    private void showInfo(CommandSender sender, boolean console) {
        String version = various.emptyOrNA(plugin.getDescription().getVersion());
        String name = various.emptyOrNA(plugin.getDescription().getName());
        String apiV = various.emptyOrNA(plugin.getDescription().getAPIVersion());
        String depend = various.listOrNA(plugin.getDescription().getDepend());
        String softDepend = various.listOrNA(plugin.getDescription().getSoftDepend());
        String authors = various.listOrNA(plugin.getDescription().getAuthors());

        sender.sendMessage(ChatColor.LIGHT_PURPLE + "=== Plugin Information ===");
        sender.sendMessage(ChatColor.YELLOW + "Name: " + ChatColor.WHITE + name);
        sender.sendMessage(ChatColor.YELLOW + "Version: " + ChatColor.WHITE + version);
        sender.sendMessage(ChatColor.YELLOW + "Authors: " + ChatColor.WHITE + authors);

        if (console) {
            sender.sendMessage(ChatColor.GREEN + "Console additional info:");
            sender.sendMessage(ChatColor.YELLOW + "API Version: " + ChatColor.WHITE + apiV);
            sender.sendMessage(ChatColor.YELLOW + "Dependencies: " + ChatColor.WHITE + depend);
            sender.sendMessage(ChatColor.YELLOW + "Soft-Dependencies: " + ChatColor.WHITE + softDepend);
        }
    }
}
