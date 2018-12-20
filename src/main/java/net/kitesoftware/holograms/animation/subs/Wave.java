/*
 * Copyright (c) 2016-2018 Niall Lindsay
 * Email: niall_lindsay@icloud.com
 */

package net.kitesoftware.holograms.animation.subs;

import net.kitesoftware.holograms.animation.BaseAnimation;
import net.kitesoftware.holograms.util.Utils;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Wave extends BaseAnimation {

    public Wave() {
        super("wave", "Creates a color wave animation", Collections.singletonMap(
                "colors", "§c,§e,§6,§a,§9,§1,§d"
        ));
    }

    @Override
    public List<String> setAnimations(String text, Map<String, String> options) {
        List<String> frames = new ArrayList<>();
        options = Utils.mergeMap(getOptions(), options);

        String[] colors = options.get("colors").split(",");

        String formatting = ChatColor.getLastColors(text);
        text = text.replace(formatting, "");

        int counter = 0;
        int index = 0;

        for (String color : colors) {
            StringBuilder cframe = new StringBuilder();


            for (char c : text.toCharArray()) {
                String result = color + formatting;

                index++;
                if (index >= colors.length) {
                    index = 0;
                }

                cframe.append(result).append(c);
            }

            index = ++counter;
            frames.add(cframe.toString());
        }
        return frames;
    }
}
