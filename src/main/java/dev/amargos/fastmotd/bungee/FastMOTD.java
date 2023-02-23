package dev.amargos.fastmotd.bungee;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

import dev.amargos.fastmotd.bungee.commands.FastMOTDCommand;
import dev.amargos.fastmotd.bungee.listeners.ProxyPingListener;
import dev.amargos.fastmotd.bungee.utils.ConfigurationUtil;
import dev.amargos.fastmotd.bungee.variables.BungeeVariables;
import dev.amargos.fastmotd.bungee.variables.Messages;

public class FastMOTD extends Plugin {
    public void onEnable() {
        final ConfigurationUtil configurationUtil = new ConfigurationUtil(this);

        configurationUtil.createConfiguration("%datafolder%/config.yml");
        configurationUtil.createConfiguration("%datafolder%/messages.yml");

        final ProxyServer proxy = getProxy();
        final BungeeVariables variables = new BungeeVariables(configurationUtil);
        final Messages messages = new Messages(configurationUtil);
        final PluginManager pluginManager = proxy.getPluginManager();

        pluginManager.registerListener(this,
                new ProxyPingListener(variables));
        pluginManager.registerCommand(this, new FastMOTDCommand("fastmotd", variables, messages));
    }
}