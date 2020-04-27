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
import net.kitesoftware.holograms.config.ConfigAnimation;
import org.bukkit.command.CommandSender;

public class CommandList extends SubCommand {

    public CommandList(CommandHandler commandHandler) {
        super("list", "Lists all configured animations", "", 0, commandHandler);
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        sender.sendMessage("§eShowing all registered animations:");

        for (ConfigAnimation animation : getCommandHandler().getPlugin().getPlaceholderRegistry().getRegisteredPlaceholders()) {
            sender.sendMessage(" - " + animation.getName() + " §7(speed: §f" + animation.getRefreshRate() + ", §7total frames: §f" + animation.getFrames().size() + "§7)");
        }

        return true;
    }
}
