/*
 * Copyright (c) 2016-2019 Niall Lindsay
 *
 */

package net.kitesoftware.holograms.animation.impl;

import net.kitesoftware.holograms.animation.iface.ConfigurableAnimation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Typewriter implements ConfigurableAnimation {

    private HashMap<String, String> DEFAULTS = new HashMap<String, String>() {{
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
