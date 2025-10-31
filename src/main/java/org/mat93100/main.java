package org.mat93100;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider; //future implementation
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import org.mat93100.commands.tabComplete;
import org.mat93100.handlers.InputHandler;

public final class main extends JavaPlugin {

    private LuckPerms luckPermsAPI;

    String LoadPrefix = ChatColor.WHITE + "[" +
            ChatColor.LIGHT_PURPLE + "93PL" +
            ChatColor.WHITE + " - " +
            ChatColor.YELLOW + "[BETA]" +
            ChatColor.WHITE + "] ";

    @Override
    public void onLoad(){
        Bukkit.getConsoleSender().sendMessage(LoadPrefix + ChatColor.GREEN + "Loading initialized.");
        Bukkit.getConsoleSender().sendMessage(LoadPrefix + ChatColor.GREEN + "Loading config...");
        //TODO
        Bukkit.getConsoleSender().sendMessage(LoadPrefix + ChatColor.GREEN + "Config loaded!");
    }

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(LoadPrefix + ChatColor.GREEN + "Started loading plugin...");
        if (!hookLuckPerms()) {
            Bukkit.getConsoleSender().sendMessage(LoadPrefix + ChatColor.RED + "LuckPerms not found! Disabling plugin...");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        Bukkit.getConsoleSender().sendMessage(LoadPrefix + ChatColor.GREEN + "LuckPerms successfully hooked!");

        getCommand("rank").setExecutor(new org.mat93100.commands.RankCommand(this));
        getCommand("rank").setTabCompleter(new tabComplete());
        getServer().getPluginManager().registerEvents(new InputHandler(), this);
    }

    private boolean hookLuckPerms() {
        if (Bukkit.getPluginManager().getPlugin("LuckPerms") == null) return false;
        RegisteredServiceProvider<LuckPerms> provider = getServer().getServicesManager().getRegistration(LuckPerms.class);
        if (provider == null) return false;
        luckPermsAPI = provider.getProvider();
        return luckPermsAPI != null;
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(LoadPrefix + ChatColor.AQUA + "Plugin unloaded!");
    }

    public LuckPerms getLuckPermsAPI() {
        return luckPermsAPI;
    }
}