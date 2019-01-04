/*
 * Copyright (c) 2016-2018 Niall Lindsay
 * Email: niall_lindsay@icloud.com
 */

package net.kitesoftware.holograms.animation.subanimation;

import net.kitesoftware.holograms.animation.iface.Animation;
import org.bukkit.ChatColor;

import java.util.*;

public class Erase implements Animation {

    @Override
    public String getName() {
        return "erase";
    }

    @Override
    public List<String> create(String text) {
        List<String> frames = new ArrayList<>();

        String lastcolor = ChatColor.getLastColors(text);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            sb.append(" ");
            String cutText = text.substring(i);

            frames.add(sb.toString() + lastcolor + cutText);
        }

        return frames;
    }

}
