/*
 * Copyright (c) 2016-2019 Niall Lindsay
 *
 */

package net.kitesoftware.holograms.command.subs;

import net.kitesoftware.holograms.UserAnimationManager;
import net.kitesoftware.holograms.command.CommandHandler;
import net.kitesoftware.holograms.command.SubCommand;
import net.kitesoftware.holograms.config.ConfigAnimation;
import net.kitesoftware.holograms.exception.AnimationNotFoundException;
import org.bukkit.command.CommandSender;

public class CommandInfo extends SubCommand {

    public CommandInfo(CommandHandler commandHandler) {
        super("info", "See animation information", "[animation] [show frames? true/false]", 0, commandHandler);
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        UserAnimationManager man = getCommandHandler().getPlugin().getUserAnimationManager();
        if (args.length == 0) {
            sender.sendMessage("§eThere are §f" + man.getRegisteredAnimations().size() + "§e Animations loaded.");
            sender.sendMessage("§7Use §f/" + label + " info §e[animation] §7For more information.");
        } else {
            String animationName = args[0];
            try {
                ConfigAnimation anim = man.getAnimationFromName(animationName);
                sender.sendMessage("§6Animation: §e" + anim.getName());
                sender.sendMessage("Refresh rate: " + anim.getRefreshRate() + " seconds");
                sender.sendMessage("No. of frames: " + anim.getFrames().size());

                if (args.length > 1 && Boolean.parseBoolean(args[1])) {
                    sender.sendMessage("Frames: ");
                    for (String frame : anim.getFrames()) {
                        sender.sendMessage(frame);
                    }
                }
            } catch (AnimationNotFoundException e) {
                sender.sendMessage("§cThis animation could not be found. §eUse /" + label + " list §cto see all registered animations.");
            }
        }


        return true;
    }
}
