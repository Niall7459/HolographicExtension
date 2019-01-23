/*
 * Copyright (c) 2016-2019 Niall Lindsay
 *
 */

package net.kitesoftware.holograms.command.subs;

import net.kitesoftware.holograms.command.SubCommand;
import org.bukkit.command.CommandSender;

public class CommandQuickstart extends SubCommand {

    //Currently unused.
    public CommandQuickstart() {
        super("quickstart", "Get some essential information on setting up and installing placeholders.", "", 0, null);
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        sender.sendMessage("§6This is a short guide on getting started with HolographicExtension.");
        return true;
    }
}
