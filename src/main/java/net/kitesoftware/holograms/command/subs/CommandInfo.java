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
import net.kitesoftware.holograms.exception.PlaceholderNotFoundException;
import net.kitesoftware.holograms.placeholder.PlaceholderRegistry;
import org.bukkit.command.CommandSender;

public class CommandInfo extends SubCommand {

    public CommandInfo(CommandHandler commandHandler) {
        super("info", "See animation information", "[animation] [show frames? true/false]", 0, commandHandler);
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        PlaceholderRegistry man = getCommandHandler().getPlugin().getPlaceholderRegistry();
        if (args.length == 0) {
            sender.sendMessage("§eThere are §f" + man.getRegisteredPlaceholders().size() + "§e animations loaded.");
            sender.sendMessage("§7Use §f/" + label + " info §e[animation] §7for more information.");
        } else {
            String animationName = args[0];
            try {
                ConfigAnimation anim = man.getPlaceholderFromName(animationName);
                sender.sendMessage("§6Animation: §e" + anim.getName());
                sender.sendMessage("§7Refresh rate: §f" + anim.getRefreshRate() + " seconds");
                sender.sendMessage("§7No. of frames: §f" + anim.getFrames().size());

                if (args.length > 1 && Boolean.parseBoolean(args[1])) {
                    sender.sendMessage("Frames: ");
                    for (int i = 0; i < anim.getFrames().size(); i++) {
                        sender.sendMessage(i + ": " + anim.getFrames().get(i));
                    }
                }
            } catch (PlaceholderNotFoundException e) {
                sender.sendMessage("§cThis animation could not be found. §eUse /" + label + " list §cto see all registered animations.");
            }
        }


        return true;
    }
}
