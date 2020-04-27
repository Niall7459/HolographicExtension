/*
 *  Holographic Extension
 *  Copyright (C) 2015 - 2019 Niall7459
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package net.kitesoftware.holograms.command.subs;

import net.kitesoftware.holograms.command.CommandHandler;
import net.kitesoftware.holograms.command.SubCommand;
import org.bukkit.command.CommandSender;

public class CommandAbout extends SubCommand {

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
