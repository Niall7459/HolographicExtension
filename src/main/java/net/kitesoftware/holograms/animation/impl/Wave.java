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
package net.kitesoftware.holograms.animation.impl;

import net.kitesoftware.holograms.animation.ConfigurableAnimation;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Wave implements ConfigurableAnimation {

    private static final Map<String, String> DEFAULTS = Collections.singletonMap(
            "colors", "§c,§e,§6,§a,§9,§1,§d");

    @Override
    public String getName() {
        return "wave";
    }

    @Override
    public Map<String, String> getOptions() {
        return DEFAULTS;
    }

    @Override
    public List<String> create(String text, Map<String, String> options) {
        List<String> frames = new ArrayList<>();
        String[] colors = options.get("colors").split(",");

        String formatting = ChatColor.getLastColors(text);
        text = text.replace(formatting, "");

        int counter = 0;
        int index = 0;

        for (String ignored : colors) {
            StringBuilder currentFrame = new StringBuilder();


            for (char c : text.toCharArray()) {
                String result = colors[index] + formatting;

                index++;
                if (index >= colors.length) {
                    index = 0;
                }

                currentFrame.append(result).append(c);
            }

            index = ++counter;
            frames.add(currentFrame.toString());
        }
        return frames;
    }

}
