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
package net.kitesoftware.holograms.listener;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.comphenix.protocol.wrappers.WrappedWatchableObject;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kitesoftware.holograms.listener.wrapper.WrapperPlayServerEntityMetadata;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Optional;

public class PacketPlaceholderListener extends PacketAdapter {
    private boolean useOptional = true;

    public PacketPlaceholderListener(AdapterParameteters params) {
        super(params);

        String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
        String majorVersion = version.split("_")[1];
        if (majorVersion.contains("_")) majorVersion = majorVersion.split("_")[0];

        if (Integer.parseInt(majorVersion) < 13) useOptional = false;
    }

    @Override
    public void onPacketSending(PacketEvent event) {
        PacketContainer packet = event.getPacket();

        if (packet.getType() != PacketType.Play.Server.ENTITY_METADATA) return;

        WrapperPlayServerEntityMetadata entityMetadataPacket = new WrapperPlayServerEntityMetadata(packet);
        Entity e = entityMetadataPacket.getEntity(event);

        if (e == null) return;
        if (e.getType() != EntityType.ARMOR_STAND) return;

        List<WrappedWatchableObject> dataWatcherValues = entityMetadataPacket.getEntityMetadata();
        if (dataWatcherValues == null) return;

        for (WrappedWatchableObject watchableObject : dataWatcherValues) {
            if (watchableObject.getIndex() == 2) {
                if (replacePlaceholders(watchableObject, event.getPlayer())) event.setPacket(entityMetadataPacket.getHandle());

                return;
            }
        }

    }

    private boolean replacePlaceholders(WrappedWatchableObject customNameWatchableObject, Player player) {
        if (customNameWatchableObject == null) return true;

        Object customNameWatchableObjectValue = customNameWatchableObject.getValue();
        String customName;

        if (useOptional) { // 1.13 or above
            if (!(customNameWatchableObjectValue instanceof Optional)) return false;

            Optional<?> customNameOptional = (Optional<?>) customNameWatchableObjectValue;
            if (!customNameOptional.isPresent()) return false;

            WrappedChatComponent componentWrapper = WrappedChatComponent.fromHandle(customNameOptional.get());
            customName = componentWrapper.getJson();

        } else {
            customName = (String) customNameWatchableObjectValue;
        }

        customName = PlaceholderAPI.setPlaceholders(player, customName);

        if (useOptional) { // 1.13 or above
            customNameWatchableObject.setValue(Optional.of(WrappedChatComponent.fromJson(customName).getHandle()));
        } else {
            customNameWatchableObject.setValue(customName);
        }

        return true;
    }

}
