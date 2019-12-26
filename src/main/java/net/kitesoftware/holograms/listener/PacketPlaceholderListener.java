/*
 * Copyright (c) 2016-2019 Niall Lindsay
 *
 */

package net.kitesoftware.holograms.listener;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.comphenix.protocol.wrappers.WrappedWatchableObject;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Optional;

public class PacketPlaceholderListener extends PacketAdapter {
    private boolean useOptional = false;

    public PacketPlaceholderListener(AdapterParameteters params) {
        super(params);

        String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
        if(version.equals("v1_13_R1") || version.equals("v1_13_R2") || version.equals("v1_14_R1") || version.equals("v1_15_R1")) {
            useOptional = true;
        }
    }

    @Override
    public void onPacketSending(PacketEvent event) {
        PacketContainer packet = event.getPacket();

        if (packet.getType() == PacketType.Play.Server.ENTITY_METADATA) {

            WrapperPlayServerEntityMetadata entityMetadataPacket = new WrapperPlayServerEntityMetadata(packet.deepClone());
            List<WrappedWatchableObject> dataWatcherValues = entityMetadataPacket.getEntityMetadata();

            for (WrappedWatchableObject watchableObject : dataWatcherValues) {
                if (watchableObject.getIndex() == 2) {
                    if (replacePlaceholders(watchableObject, event.getPlayer())) {
                        event.setPacket(entityMetadataPacket.getHandle());
                    }

                    return;
                }
            }
        }
    }

    private boolean replacePlaceholders(WrappedWatchableObject customNameWatchableObject, Player player) {
        if (customNameWatchableObject == null) return true;

        Object customNameWatchableObjectValue = customNameWatchableObject.getValue();
        String customName;

        if (useOptional) { //1.13 or above
            if (!(customNameWatchableObjectValue instanceof Optional)) {
                return false;
            }

            Optional<?> customNameOptional = (Optional<?>) customNameWatchableObjectValue;
            if (!customNameOptional.isPresent()) {
                return false;
            }

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
