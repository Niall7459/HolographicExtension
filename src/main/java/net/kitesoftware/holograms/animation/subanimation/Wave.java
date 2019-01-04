/*
 * Copyright (c) 2016-2018 Niall Lindsay
 * Email: niall_lindsay@icloud.com
 */

package net.kitesoftware.holograms.animation.subanimation;

import net.kitesoftware.holograms.animation.iface.Animation;
import net.kitesoftware.holograms.animation.iface.ConfigurableAnimation;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Wave implements ConfigurableAnimation {

    private Map<String, String> options = Collections.singletonMap(
            "colors", "§c,§e,§6,§a,§9,§1,§d");

    @Override
    public String getName() {
        return "wave";
    }

    @Override
    public Map<String, String> getOptions() {
        return options;
    }

    @Override
    public List<String> create(String text, Map<String, String> options) {
        List<String> frames = new ArrayList<>();
        String[] colors = options.get("colors").split(",");

        String formatting = ChatColor.getLastColors(text);
        text = text.replace(formatting, "");

        int counter = 0;
        int index = 0;

        for (String ignored : colors) {
            StringBuilder cframe = new StringBuilder();


            for (char c : text.toCharArray()) {
                String result = colors[index] + formatting;

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
