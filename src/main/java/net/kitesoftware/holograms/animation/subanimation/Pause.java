/*
 * Copyright (c) 2016-2018 Niall Lindsay
 * Email: niall_lindsay@icloud.com
 */

package net.kitesoftware.holograms.animation.subanimation;

import net.kitesoftware.holograms.animation.iface.Animation;
import net.kitesoftware.holograms.animation.iface.ConfigurableAnimation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pause implements Animation, ConfigurableAnimation {

    private Map<String, String> options = new HashMap<String, String>() {{
        put("times", "10");
    }};

    @Override
    public String getName() {
        return "pause";
    }

    @Override
    public Map<String, String> getOptions() {
        return options;
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
