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

public class Glow implements ConfigurableAnimation {

    private static final HashMap<String, String> DEFAULTS = new HashMap<String, String>() {{
        put("normal", "§e");
        put("start", "§d");
        put("middle", "§5");
        put("end", "§d");
        put("size", "3");
        put("pause", "10");
    }};

    @Override
    public String getName() {
        return "glow";
    }

    @Override
    public Map<String, String> getOptions() {
        return DEFAULTS;
    }

    @Override
    public List<String> create(String text, Map<String, String> options) {
        List<String> frames = new ArrayList<>();

        String normalColor = options.get("normal");
        String startColor = options.get("start");
        String middleColor = options.get("middle");
        String endColor = options.get("end");

        int glowSize = Integer.parseInt(options.get("size"));
        int pauseFrames = Integer.parseInt(options.get("pause"));

        for (int i = 0; i < text.length() + glowSize; i++) {
            int startGi = Math.max(i - glowSize, 0);
            int midGi = Math.max(startGi + (startGi > 0 ? 1 : 0), 0) + (i - glowSize == 0 ? 1 : 0);

            String frame = normalColor + text.substring(0, startGi) +
                    startColor + text.substring(Math.min(Math.max(startGi, 0), startGi), Math.min(midGi, text.length())) +
                    middleColor + text.substring(midGi, Math.min(Math.max(i - 1, 0), text.length())) +
                    endColor + text.substring(Math.max(Math.min(i - 1, text.length()), 0), Math.min(i, text.length())) +
                    normalColor + text.substring(Math.min(i, text.length()));
            frames.add(frame);
        }

        for (int i = 0; i < pauseFrames; i++) {
            frames.add(normalColor + text);
        }

        return frames;
    }
}
