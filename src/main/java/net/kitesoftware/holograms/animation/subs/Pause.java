/*
 * Copyright (c) 2016-2018 Niall Lindsay
 * Email: niall_lindsay@icloud.com
 */

package net.kitesoftware.holograms.animation.subs;

import net.kitesoftware.holograms.animation.BaseAnimation;
import net.kitesoftware.holograms.util.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pause extends BaseAnimation {

    public Pause() {
        super("pause", "Pause text a certain amount of frames", new HashMap<String, String>() {{
            put("times", "10");
        }});
    }

    @Override
    public List<String> setAnimations(String text, Map<String, String> options) {
        List<String> frames = new ArrayList<>();
        options = Utils.mergeMap(getOptions(), options);

        int pause = Integer.parseInt(options.get("times"));
        for (int i = 0; i < pause; i++) {
            frames.add(text);
        }

        return frames;
    }
}
