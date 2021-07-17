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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Typewriter implements ConfigurableAnimation {

    private final HashMap<String, String> DEFAULTS = new HashMap<String, String>() {{
        put("cursor", "_");
        put("pause", "10");
        put("reverse", "true");
    }};

    @Override
    public String getName() {
        return "typewriter";
    }

    @Override
    public Map<String, String> getOptions() {
        return DEFAULTS;
    }

    @Override
    public List<String> create(String text, Map<String, String> options) {
        List<String> frames = new ArrayList<>();

        String cursor = options.get("cursor");
        int pauseTime = Integer.parseInt(options.get("pause"));
        boolean reverse = Boolean.parseBoolean(options.get("reverse"));

        StringBuilder current = new StringBuilder();
        int counter = 0;

        for (int i = 0; i < pauseTime; i++) {
            frames.add("");
            frames.add("");
            frames.add(cursor);
            frames.add(cursor);
        }

        for (char character : text.toCharArray()) {
            current.append(character);
            counter = counterCheck(frames, cursor, current, counter);

        }

        for (int i = 0; i < pauseTime; i++) {
            counter = counterCheck(frames, cursor, current, counter);
        }

        if (reverse) {
            for (int i = text.length(); i > 0; i--) {
                current.deleteCharAt(i - 1);
                counter = counterCheck(frames, cursor, current, counter);
            }
        }

        return frames;
    }

    private int counterCheck(List<String> frames, String cursor, StringBuilder current, int counter) {
        if (counter < 2) {
            frames.add(current + "");
        } else {
            frames.add(current + cursor);
        }

        if (counter == 4) {
            counter = 0;
        } else {
            counter += 1;
        }
        return counter;
    }

}
