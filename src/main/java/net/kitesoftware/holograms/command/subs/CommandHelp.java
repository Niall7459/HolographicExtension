/*
 * Copyright (c) 2016-2018 Niall Lindsay
 * Email: niall_lindsay@icloud.com
 */

package net.kitesoftware.holograms.command.subs;

import net.kitesoftware.holograms.command.BaseCommand;
import net.kitesoftware.holograms.command.CommandHandler;
import org.bukkit.command.CommandSender;

public class CommandHelp extends BaseCommand {

    private CommandHandler commandHandler;

    public CommandHelp(CommandHandler commandHandler) {
        super("help", "Display all commands", "", 0);
        this.commandHandler = commandHandler;
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {

        for (BaseCommand command : commandHandler.getCommands()) {
            sender.sendMessage("§e/" + label + " " + command.getName() + " " + command.getPossibleArgs());
            sender.sendMessage("§7" + command.getDescription());
        }

        return true;
    }
}
