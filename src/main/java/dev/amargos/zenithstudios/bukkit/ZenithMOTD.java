package dev.amargos.zenithstudios.bukkit;

import dev.amargos.zenithstudios.bukkit.commands.FastMOTDCommand;
import dev.amargos.zenithstudios.bukkit.listeners.ServerListPingListener;
import dev.amargos.zenithstudios.bukkit.utils.ConfigurationUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import dev.amargos.zenithstudios.bukkit.listeners.ServerInfoListener;
import dev.amargos.zenithstudios.bukkit.variables.BukkitVariables;
import dev.amargos.zenithstudios.bukkit.variables.Messages;

public class ZenithMOTD extends JavaPlugin {
    @Override
    public void onEnable() {
        final ConfigurationUtil configurationUtil = new ConfigurationUtil(this);

        configurationUtil.createConfiguration("%datafolder%/Config.yml");
        configurationUtil.createConfiguration("%datafolder%/Messages.yml");

        final Server server = getServer();
        final BukkitVariables variables = new BukkitVariables(configurationUtil);
        final Messages messages = new Messages(configurationUtil);
        final PluginManager pluginManager = server.getPluginManager();

        Bukkit.getConsoleSender().sendMessage(ChatColor.GRAY+"--------------------------------");
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"ZenithMOTD"+ChatColor.WHITE+" It was successfully enabled! (version: "+ChatColor.RED+"2.0.0"+ChatColor.WHITE+")");
        Bukkit.getConsoleSender().sendMessage(ChatColor.WHITE+"Thanks for using my plugin"+ChatColor.RED+"- Mode");
        Bukkit.getConsoleSender().sendMessage(ChatColor.GRAY+"--------------------------------");

        getCommand("fastmotd").setExecutor(new FastMOTDCommand(variables, messages));

        if (pluginManager.isPluginEnabled("ProtocolLib")) {
            new ServerInfoListener(this, variables).register();
        } else {
            pluginManager.registerEvents(new ServerListPingListener(variables), this);
        }
    }
}