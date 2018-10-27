/*
 * Copyright (c) 2016-2018 Niall Lindsay
 * Email: niall_lindsay@icloud.com
 */

package net.kitesoftware.holograms.animation.subs;

import net.kitesoftware.holograms.animation.BaseAnimation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Rainbow extends BaseAnimation {

    public Rainbow() {
        super("rainbow", "Creates rainbow text", null);
    }

    @Override
    public List<String> setAnimations(String text, Map<String, String> options) {
        List<String> frames = new ArrayList<>();

        for(int i = 0; i < 9; ++i)
            frames.add("§" + i + text);

        for(int i = 97; i < 102; ++i)
            frames.add("§" + Character.toString((char) i) + text);

        return frames;
    }
}
