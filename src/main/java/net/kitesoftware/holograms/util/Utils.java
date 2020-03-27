/*
 * Copyright (c) 2016-2019 Niall Lindsay
 *
 */

package net.kitesoftware.holograms.util;

import net.kitesoftware.holograms.animation.AnimationRegistry;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {
    public static Map<String, String> mergeMap(Map<String, String> defaults, Map<String, String> overrides) {
        Map<String, String> mergedMap = new HashMap<>(defaults);
        for (String key : defaults.keySet())
            if (overrides.containsKey(key)) mergedMap.put(key, overrides.get(key));

        return mergedMap;
    }

    public static List<String> insertList(String before, List<String> list, String after) {
        List<String> out = new ArrayList<>();
        for (String frame : list) {
            out.add(before + frame + after);
        }
        return out;
    }

    public static Map<String, String> decodeOptions(String text) {
        Map<String, String> options = new HashMap<>();

        if (!text.contains(" ")) {
            return parseParametersToMap(text, options);
        }

        for (String parameter : text.split(" ")) {
            parseParametersToMap(parameter, options);
        }

        return options;
    }

    private static Map<String, String> parseParametersToMap(String parameter, Map<String, String> map) {
        if (parameter.contains("=")) {
            String[] parts = parameter.split("=", 2);
            map.put(parts[0], parts[1]);
        }

        return map;
    }

    public static List<String> setAnimations(List<String> frames, AnimationRegistry register) {
        List<String> outputFrames = new ArrayList<>();

        for (String frame : frames) {
            frame = ChatColor.translateAlternateColorCodes('&', frame);
            outputFrames.addAll(register.setAnimations(frame));
        }

        if (frames.size() < 1) {
            outputFrames.add("Failed to load this animation");
        }

        return outputFrames;
    }

    public static String alignText(String text, int width, boolean right) {
        StringBuilder space = new StringBuilder();

        for (int i = 0; i < (width - text.length()); i++) {
            space.append(" ");
        }

        return right ? (space + text) : (text + space);
    }
}
