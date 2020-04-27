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

import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import net.kitesoftware.holograms.HolographicExtension;
import net.kitesoftware.holograms.command.CommandHandler;
import net.kitesoftware.holograms.command.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class CommandReload extends SubCommand {

    public CommandReload(CommandHandler commandHandler) {
        super("reload", "Reload configuration files", "", 0, commandHandler);
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        HolographicExtension plugin = getCommandHandler().getPlugin();
        HologramsAPI.unregisterPlaceholders(plugin);

        plugin.getPlaceholderRegistry().getRegisteredPlaceholders().clear();
        plugin.getConfigManager().reload();

        if (plugin.getProtocolHook() != null) {
            plugin.getProtocolHook().disable();
            plugin.hookProtocolLib();
        }

        sender.sendMessage(ChatColor.GREEN + "Configuration files have been reloaded");
        return true;
    }
}
