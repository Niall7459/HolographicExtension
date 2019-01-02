/*
 * Copyright (c) 2016-2018 Niall Lindsay
 * Email: niall_lindsay@icloud.com
 */

package net.kitesoftware.holograms.animation.subanimation;

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
