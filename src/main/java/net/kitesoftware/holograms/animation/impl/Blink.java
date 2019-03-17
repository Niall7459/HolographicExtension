/*
 * Copyright (c) 2016-2019 Niall Lindsay
 *
 */

package net.kitesoftware.holograms.animation.impl;

import net.kitesoftware.holograms.animation.iface.Animation;

import java.util.Arrays;
import java.util.List;

public class Blink implements Animation {

    @Override
    public String getName() {
        return "blink";
    }

    @Override
    public List<String> create(String text) {
        return Arrays.asList(text, "");
    }
}
