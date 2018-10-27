/*
 * Copyright (c) 2016-2018 Niall Lindsay
 * Email: niall_lindsay@icloud.com
 */

package net.kitesoftware.holograms.animation.subs;

import net.kitesoftware.holograms.animation.BaseAnimation;
import net.kitesoftware.holograms.util.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Right extends BaseAnimation {

    public Right() {
        super("right", "Align text to the right",
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

        frames.add(space + text);
        return frames;
    }
}
