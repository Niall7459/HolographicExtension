/*
 * Copyright (c) 2016-2018 Niall Lindsay
 * Email: niall_lindsay@icloud.com
 */

package net.kitesoftware.holograms.command.subs;

import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import net.kitesoftware.holograms.HolographicExtension;
import net.kitesoftware.holograms.command.BaseCommand;
import net.kitesoftware.holograms.command.CommandHandler;
import net.kitesoftware.holograms.placeholder.RefreshPlaceholders;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class CommandReload extends BaseCommand {

    public CommandReload(CommandHandler commandHandler) {
        super("reload", "Reload configuration files", "", 0, commandHandler);
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        HolographicExtension plugin = getCommandHandler().getPlugin();

        HologramsAPI.unregisterPlaceholders(plugin);

        new RefreshPlaceholders().register(plugin);
        plugin.getUserAnimationManager().getRegisteredAnimations().clear();
        plugin.getConfigManager().reload();

        if (plugin.getProtocolHook() != null) {
            plugin.getProtocolHook().disable();
            plugin.hookProtocolLib();
        }

        sender.sendMessage(ChatColor.GREEN + "Configuration files have been reloaded");
        return true;
    }
}
