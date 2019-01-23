/*
 * Copyright (c) 2016-2019 Niall Lindsay
 *
 */

package net.kitesoftware.holograms.animation.subanimation;

import net.kitesoftware.holograms.animation.iface.ConfigurableAnimation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pause implements ConfigurableAnimation {

    private static final Map<String, String> DEFAULTS = new HashMap<String, String>() {{
        put("times", "10");
    }};

    @Override
    public String getName() {
        return "pause";
    }

    @Override
    public Map<String, String> getOptions() {
        return DEFAULTS;
    }

    @Override
    public List<String> create(String text, Map<String, String> options) {
        List<String> frames = new ArrayList<>();

        int pause = Integer.parseInt(options.get("times"));
        for (int i = 0; i < pause; i++) {
            frames.add(text);
        }

        return frames;
    }
}
