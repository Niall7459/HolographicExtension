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

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Align {
    public static final ConfigurableAnimation LEFT = new ConfigurableAnimation() {
        @Override
        public String getName() {
            return "left";
        }

        @Override
        public Map<String, String> getOptions() {
            return Collections.singletonMap("width", "32");
        }

        @Override
        public List<String> create(String text, Map<String, String> options) {
            return Collections.singletonList(alignText(
                    text, Integer.parseInt(options.get("width")), false));
        }
    };

    public static final ConfigurableAnimation RIGHT = new ConfigurableAnimation() {
        @Override
        public String getName() {
            return "right";
        }

        @Override
        public Map<String, String> getOptions() {
            return Collections.singletonMap("width", "32");
        }

        @Override
        public List<String> create(String text, Map<String, String> options) {
            return Collections.singletonList(alignText(
                    text, Integer.parseInt(options.get("width")), true));
        }
    };

    private static String alignText(String text, int width, boolean right) {
        StringBuilder space = new StringBuilder();

        for (int i = 0; i < (width - text.length()); i++) {
            space.append(" ");
        }

        return right ? (space + text) : (text + space);
    }
}