/*
 * Copyright (c) 2016-2018 Niall Lindsay
 * Email: niall_lindsay@icloud.com
 */

package net.kitesoftware.holograms.command.subs;

import net.kitesoftware.holograms.HolographicExtension;
import net.kitesoftware.holograms.command.BaseCommand;
import net.kitesoftware.holograms.command.CommandHandler;
import net.kitesoftware.holograms.config.ConfigAnimation;
import org.bukkit.command.CommandSender;

public class CommandList extends BaseCommand {

    public CommandList() {
        super("list", "Lists all configured animations", "", 0);
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        sender.sendMessage("§eShowing all registered animations:");

        for (ConfigAnimation animation : HolographicExtension.getInstance().getUserAnimationManager().getRegisteredAnimations()) {
            sender.sendMessage(" - " + animation.getName() + " §7(Speed: §f" + animation.getRefreshRate() + " §7Frames: " + animation.getFrames().size());
        }

        return true;
    }
}
