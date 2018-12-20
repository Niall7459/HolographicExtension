/*
 * Copyright (c) 2016-2018 Niall Lindsay
 * Email: niall_lindsay@icloud.com
 */

package net.kitesoftware.holograms.command.subs;

import net.kitesoftware.holograms.command.BaseCommand;
import net.kitesoftware.holograms.command.CommandHandler;
import org.bukkit.command.CommandSender;

public class CommandAbout extends BaseCommand {

    public CommandAbout(CommandHandler commandHandler) {
        super("about", "Display plugin information", "", 0, commandHandler);
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        sender.sendMessage("§6HolographicExtension §e" + getCommandHandler().getPlugin().getDescription().getVersion());
        sender.sendMessage("§6Authors: §e" + getCommandHandler().getPlugin().getDescription().getAuthors());
        sender.sendMessage("§7" + getCommandHandler().getPlugin().getDescription().getDescription());
        return true;
    }
}
