/*
 * Copyright (c) 2016-2018 Niall Lindsay
 * Email: niall_lindsay@icloud.com
 */

package net.kitesoftware.holograms.command.subs;

import net.kitesoftware.holograms.command.BaseCommand;
import org.bukkit.command.CommandSender;

public class CommandQuickstart extends BaseCommand {

    public CommandQuickstart() {
        super("quickstart", "Get some essential information on setting up and installing placeholders.", "", 0);
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        sender.sendMessage("§6This is a short guide on getting started with HolographicExtension.");
        return true;
    }
}
