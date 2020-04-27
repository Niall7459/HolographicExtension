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
package net.kitesoftware.holograms.placeholder;

import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import net.kitesoftware.holograms.HolographicExtension;
import net.kitesoftware.holograms.config.ConfigAnimation;
import net.kitesoftware.holograms.exception.PlaceholderNotFoundException;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

public class PlaceholderRegistry {
    private final HolographicExtension plugin;
    private final List<ConfigAnimation> placeholderList;
    private static final String PLACEHOLDER_PREFIX = "ext:";

    public PlaceholderRegistry(HolographicExtension plugin) {
        placeholderList = new ArrayList<>();
        this.plugin = plugin;
    }

    public void registerPlaceholder(ConfigAnimation animation) {
        placeholderList.add(animation);

        Bukkit.getConsoleSender().sendMessage("§e[HolographicExtension] §7Registering placeholder '" + animation.getName() + "' from config.");
        String placeholder = "{" + PLACEHOLDER_PREFIX + animation.getName() + "}";
        HologramsAPI.registerPlaceholder(plugin, placeholder, animation.getRefreshRate(), new PlaceholderAnimationReplacer(animation.getFrames()));
    }

    public List<ConfigAnimation> getRegisteredPlaceholders() {
        return placeholderList;
    }

    public ConfigAnimation getPlaceholderFromName(String name) throws PlaceholderNotFoundException {
        for (ConfigAnimation animation : placeholderList) {
            if (animation.getName().equalsIgnoreCase(name)) return animation;
        }
        throw new PlaceholderNotFoundException();
    }
}
