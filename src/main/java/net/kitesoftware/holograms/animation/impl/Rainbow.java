/*
 * Copyright (c) 2016-2019 Niall Lindsay
 *
 */

package net.kitesoftware.holograms.animation.impl;

import net.kitesoftware.holograms.animation.iface.Animation;

import java.util.ArrayList;
import java.util.List;

public class Rainbow implements Animation {

    @Override
    public String getName() {
        return "rainbow";
    }

    @Override
    public List<String> create(String text) {
        List<String> frames = new ArrayList<>();

        for (int i = 0; i < 9; ++i)
            frames.add("§" + i + text);

        for (int i = 97; i < 102; ++i)
            frames.add("§" + (char) i + text);

        return frames;

    }

}
