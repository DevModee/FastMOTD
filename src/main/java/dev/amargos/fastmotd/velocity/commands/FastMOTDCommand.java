package dev.amargos.fastmotd.velocity.commands;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.velocitypowered.api.command.SimpleCommand;

import dev.amargos.fastmotd.velocity.FastMOTD;
import dev.amargos.fastmotd.velocity.utils.Components;

public final class FastMOTDCommand implements SimpleCommand {
    private final FastMOTD plugin;

    public FastMOTDCommand(FastMOTD plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(Invocation invocation) {
        final String[] args = invocation.arguments();
        if (args.length < 1 || args[0].equalsIgnoreCase("help")) {
            invocation.source().sendMessage(Components.SERIALIZER.deserialize(plugin.getMessages().usage()));
        } else if (args[0].equalsIgnoreCase("reload")) {
            plugin.reload();
            invocation.source().sendMessage(Components.SERIALIZER.deserialize(plugin.getMessages().reload()));
        } else {
            invocation.source().sendMessage(Components.SERIALIZER.deserialize(plugin.getMessages().unknownCommand()));
        }
    }

    @Override
    public boolean hasPermission(Invocation invocation) {
        return invocation.source().hasPermission("fastmotd.admin");
    }

    @Override
    public List<String> suggest(Invocation invocation) {
        final String[] args = invocation.arguments();

        if (args.length != 1) {
            return Arrays.asList("help", "reload");
        }

        if ("help".startsWith(args[0])) {
            return Collections.singletonList("help");
        }

        if ("reload".startsWith(args[0])) {
            return Collections.singletonList("reload");
        }

        return Collections.emptyList();
    }

}