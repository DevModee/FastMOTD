package dev.amargos.zenithstudios.bukkit.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import dev.amargos.zenithstudios.bukkit.variables.BukkitVariables;

public class ServerListPingListener implements Listener {
    private final BukkitVariables variables;

    public ServerListPingListener(final BukkitVariables variables) {
        this.variables = variables;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onServerListPing(final ServerListPingEvent event) {
        // There is no way to get player name on Bukkit
        // There is no way to set player count on Bukkit

        final int onlinePlayers = event.getNumPlayers();
        int maxPlayers = event.getMaxPlayers();

        if (variables.isMaxPlayersEnabled()) {
            maxPlayers = variables.isMaxPlayersJustOneMore() ? onlinePlayers + 1 : variables.getMaxPlayers();

            event.setMaxPlayers(maxPlayers);
        }

        if (variables.isMotdEnabled()) {
            event.setMotd(variables.getMOTD(maxPlayers, onlinePlayers));
        }
    }
}