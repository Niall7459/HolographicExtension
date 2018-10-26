package net.kitesoftware.holograms;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import net.kitesoftware.holograms.listener.PacketPlaceholderListener;
import org.bukkit.plugin.Plugin;

public class ProtocolHook {

    private Plugin plugin;

    void hook(Plugin plugin) {
        this.plugin = plugin;

        PacketAdapter.AdapterParameteters params = PacketAdapter
                .params()
                .plugin(plugin)
                .types(	PacketType.Play.Server.SPAWN_ENTITY_LIVING,
                        PacketType.Play.Server.SPAWN_ENTITY,
                        PacketType.Play.Server.ENTITY_METADATA)
                .serverSide()
                .listenerPriority(ListenerPriority.NORMAL);

        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketPlaceholderListener(params));
    }

    public void disable() {
        ProtocolLibrary.getProtocolManager().removePacketListeners(plugin);
    }

}
