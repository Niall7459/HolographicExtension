/*
 * Copyright (c) 2016-2019 Niall Lindsay
 *
 */

package net.kitesoftware.holograms.command.subs;

import net.kitesoftware.holograms.command.CommandHandler;
import net.kitesoftware.holograms.command.SubCommand;
import org.bukkit.command.CommandSender;

public class CommandHelp extends SubCommand {

    public CommandHelp(CommandHandler commandHandler) {
        super("help", "Display all commands", "", 0, commandHandler);
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {

        for (SubCommand command : getCommandHandler().getCommands()) {
            sender.sendMessage("§e/" + label + " " + command.getName() + " " + command.getPossibleArgs());
            sender.sendMessage("§7" + command.getDescription());
        }

        return true;
    }
}
