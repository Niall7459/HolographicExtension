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
package net.kitesoftware.holograms.hook;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import net.kitesoftware.holograms.listener.PacketPlaceholderListener;
import net.kitesoftware.holograms.placeholder.PlaceholderDefaults;
import org.bukkit.plugin.Plugin;

public class ProtocolHook {
    private Plugin plugin;

    public void enable(Plugin plugin) {
        this.plugin = plugin;

        PacketAdapter.AdapterParameteters params = PacketAdapter
                .params()
                .plugin(plugin)
                .types(PacketType.Play.Server.ENTITY_METADATA)
                .serverSide()
                .listenerPriority(ListenerPriority.NORMAL);

        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketPlaceholderListener(params));
        PlaceholderDefaults.registerRefreshPlaceholders(plugin);
    }

    public void disable() {
        ProtocolLibrary.getProtocolManager().removePacketListeners(plugin);
    }
}
