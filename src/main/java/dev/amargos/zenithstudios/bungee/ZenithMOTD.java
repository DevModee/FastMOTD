package dev.amargos.zenithstudios.bungee;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

import dev.amargos.zenithstudios.bungee.commands.ZenithMOTDCommand;
import dev.amargos.zenithstudios.bungee.listeners.ProxyPingListener;
import dev.amargos.zenithstudios.bungee.utils.ConfigurationUtil;
import dev.amargos.zenithstudios.bungee.variables.BungeeVariables;
import dev.amargos.zenithstudios.bungee.variables.Messages;

public class ZenithMOTD extends Plugin {
    public void onEnable() {
        final ConfigurationUtil configurationUtil = new ConfigurationUtil(this);

        configurationUtil.createConfiguration("%datafolder%/Config.yml");
        configurationUtil.createConfiguration("%datafolder%/Messages.yml");

        final ProxyServer proxy = getProxy();
        final BungeeVariables variables = new BungeeVariables(configurationUtil);
        final Messages messages = new Messages(configurationUtil);
        final PluginManager pluginManager = proxy.getPluginManager();

        pluginManager.registerListener(this,
                new ProxyPingListener(variables));
        pluginManager.registerCommand(this, new ZenithMOTDCommand("zenithmotd", variables, messages));
    }
}