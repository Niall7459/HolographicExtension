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
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.List;

public class PlaceholderDefaults {
    private static final List<String> changingTextImitation = Arrays.asList("§o §r", "§r §r");

    public static void registerRefreshPlaceholders(Plugin plugin) {
        HologramsAPI.registerPlaceholder(plugin, "{fastest}", 0.1,
                new PlaceholderAnimationReplacer(changingTextImitation));

        HologramsAPI.registerPlaceholder(plugin, "{fast}", 0.5,
                new PlaceholderAnimationReplacer(changingTextImitation));

        HologramsAPI.registerPlaceholder(plugin, "{medium}", 1,
                new PlaceholderAnimationReplacer(changingTextImitation));

        HologramsAPI.registerPlaceholder(plugin, "{slow}", 5,
                new PlaceholderAnimationReplacer(changingTextImitation));

        HologramsAPI.registerPlaceholder(plugin, "{slower}", 10,
                new PlaceholderAnimationReplacer(changingTextImitation));

        HologramsAPI.registerPlaceholder(plugin, "{slowest}", 45,
                new PlaceholderAnimationReplacer(changingTextImitation));
    }
}
