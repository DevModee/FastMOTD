package dev.amargos.fastmotd.bukkit;

import dev.amargos.fastmotd.bukkit.commands.FastMOTDCommand;
import dev.amargos.fastmotd.bukkit.listeners.ServerListPingListener;
import dev.amargos.fastmotd.bukkit.utils.ConfigurationUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import dev.amargos.fastmotd.bukkit.listeners.ServerInfoListener;
import dev.amargos.fastmotd.bukkit.variables.BukkitVariables;
import dev.amargos.fastmotd.bukkit.variables.Messages;

public class FastMOTD extends JavaPlugin {
    @Override
    public void onEnable() {
        final ConfigurationUtil configurationUtil = new ConfigurationUtil(this);

        configurationUtil.createConfiguration("%datafolder%/config.yml");
        configurationUtil.createConfiguration("%datafolder%/messages.yml");

        final Server server = getServer();
        final BukkitVariables variables = new BukkitVariables(configurationUtil);
        final Messages messages = new Messages(configurationUtil);
        final PluginManager pluginManager = server.getPluginManager();

        Bukkit.getConsoleSender().sendMessage(ChatColor.GRAY+"--------------------------------");
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"FastMOTD"+ChatColor.WHITE+" It was successfully enabled! (version: "+ChatColor.RED+"1.0.1"+ChatColor.WHITE+")");
        Bukkit.getConsoleSender().sendMessage(ChatColor.WHITE+"Thanks for using my plugin"+ChatColor.RED+"- Mode#1000");
        Bukkit.getConsoleSender().sendMessage(ChatColor.GRAY+"--------------------------------");

        getCommand("fastmotd").setExecutor(new FastMOTDCommand(variables, messages));

        if (pluginManager.isPluginEnabled("ProtocolLib")) {
            new ServerInfoListener(this, variables).register();
        } else {
            pluginManager.registerEvents(new ServerListPingListener(variables), this);
        }
    }
}