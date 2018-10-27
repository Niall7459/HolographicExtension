/*
 * Copyright (c) 2016-2018 Niall Lindsay
 * Email: niall_lindsay@icloud.com
 */

package net.kitesoftware.holograms.animation.subs;

import net.kitesoftware.holograms.animation.BaseAnimation;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Fadeout extends BaseAnimation {

    public Fadeout() {
        super("fadeout", "Fade text from white to black", null);
    }

    @Override
    public List<String> setAnimations(String text, Map<String, String> options) {
        return Arrays.asList(
                "§f" + text,
                "§7" + text,
                "§8" + text,
                "§0" + text
        );
    }
}
