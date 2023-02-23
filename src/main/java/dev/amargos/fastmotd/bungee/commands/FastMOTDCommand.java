package dev.amargos.fastmotd.bungee.commands;

import dev.amargos.fastmotd.bungee.variables.Messages;
import dev.amargos.fastmotd.bungee.variables.BungeeVariables;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

public class FastMOTDCommand extends Command {
    private final BungeeVariables variables;
    private final Messages messages;

    public FastMOTDCommand(final String string, final BungeeVariables variables, final Messages messages) {
        super(string);
        this.variables = variables;
        this.messages = messages;
    }

    public void execute(final CommandSender commandSender, final String[] args) {
        if (commandSender.hasPermission("fastmotd.admin")) {
            if (args.length < 1 || args[0].equalsIgnoreCase("help"))
                commandSender.sendMessage(new TextComponent(messages.getUsage()));
            else if (args[0].equalsIgnoreCase("reload")) {
                variables.reloadConfig();
                messages.reload();
                commandSender.sendMessage(new TextComponent(messages.getReload()));
            } else
                commandSender.sendMessage(new TextComponent(messages.getUnknownCommand()));
        } else
            commandSender.sendMessage(new TextComponent(messages.getNoPermission()));
    }
}