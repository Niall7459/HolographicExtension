/*
 * Copyright (c) 2016-2019 Niall Lindsay
 *
 */

package net.kitesoftware.holograms.animation.impl;

import net.kitesoftware.holograms.animation.iface.Animation;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class Erase implements Animation {

    @Override
    public String getName() {
        return "erase";
    }

    @Override
    public List<String> create(String text) {
        List<String> frames = new ArrayList<>();

        String lastColor = ChatColor.getLastColors(text);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            sb.append(" ");
            String cutText = text.substring(i);

            frames.add(sb.toString() + lastColor + cutText);
        }

        return frames;
    }

}
