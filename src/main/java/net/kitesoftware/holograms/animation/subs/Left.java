/*
 * Copyright (c) 2016-2018 Niall Lindsay
 * Email: niall_lindsay@icloud.com
 */

package net.kitesoftware.holograms.animation.subs;

import com.google.common.collect.Maps;
import net.kitesoftware.holograms.animation.BaseAnimation;
import net.kitesoftware.holograms.util.Utils;

import java.util.*;

public class Left extends BaseAnimation {

    public Left() {
        super("left", "Align text to the left",
                Collections.singletonMap("width", "32"));
    }

    @Override
    public List<String> setAnimations(String text, Map<String, String> options) {
        List<String> frames = new ArrayList<>();
        options = Utils.mergeMap(getOptions(), options);

        int width = Integer.parseInt(options.get("width"));
        StringBuilder space = new StringBuilder();

        for(int i = 0; i < (width - text.length()); i++) {
            space.append(" ");
        }

        frames.add(text + space);
        return frames;
    }
}
