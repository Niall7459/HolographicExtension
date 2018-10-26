package net.kitesoftware.holograms.command.subs;

import net.kitesoftware.holograms.HolographicExtension;
import net.kitesoftware.holograms.command.BaseCommand;
import net.kitesoftware.holograms.command.CommandHandler;
import org.bukkit.command.CommandSender;

public class CommandAbout extends BaseCommand {

    public CommandAbout() {
        super("about", "Display plugin information", "", 0);
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        sender.sendMessage("§6HolographicExtension §e" + HolographicExtension.getVersion());
        sender.sendMessage("§6Authors: §e" + HolographicExtension.getInstance().getDescription().getAuthors());
        sender.sendMessage("§7" + HolographicExtension.getInstance().getDescription().getDescription());
        return true;
    }
}
