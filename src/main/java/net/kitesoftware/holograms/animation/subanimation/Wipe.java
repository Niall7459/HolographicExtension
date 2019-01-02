/*
 * Copyright (c) 2016-2018 Niall Lindsay
 * Email: niall_lindsay@icloud.com
 */

package net.kitesoftware.holograms.animation.subanimation;

import net.kitesoftware.holograms.animation.iface.Animation;

import java.util.*;

public class Wipe implements Animation {

    @Override
    public String getName() {
        return "wipe";
    }

    @Override
    public List<String> create(String text) {
        List<String> frames = new ArrayList<>();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            sb.append(" ");
            String cutText = text.substring(i);

            frames.add(sb.toString() + cutText);
        }

        return frames;
    }

}
