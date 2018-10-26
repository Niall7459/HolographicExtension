package net.kitesoftware.holograms.util;

import net.kitesoftware.holograms.HolographicExtension;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {

    public static Map<String, String> mergeMap(Map<String, String> defaults, Map<String, String> overrides) {
        Map<String, String> mergedMap = new HashMap<>(defaults);
        for (String key : defaults.keySet())
            if(overrides.containsKey(key)) mergedMap.put(key, overrides.get(key));

        return mergedMap;
    }

    public static List<String> insertList(String before, List<String> list, String after) {
        List<String> out = new ArrayList<>();
        for (String frame : list) {
            out.add(before + frame + after);
        }
        return out;
    }

    public static HashMap<String, String> decodeOptions(String text) {
        text = text.replace("<","").replace(">", "");

        HashMap<String, String> options = new HashMap<>();
        if(!text.contains(" ")) return options;

        for(String keyvalue : text.split(" ")) {
            if(!keyvalue.contains("=")) continue;
            String key = keyvalue.split("=")[0];

            String value = keyvalue.split("=")[1];
            value = value.replace("\"", "");

            options.put(key, value);
        }

        return options;
    }

    public static List<String> setAnimations(List<String> frames) {
        List<String> outputFrames = new ArrayList<>();

        for (String frame : frames) {
            frame = ChatColor.translateAlternateColorCodes('&', frame);
            outputFrames.addAll(HolographicExtension.getInstance().getAnimationRegister().setAnimations(frame));
        }

        return outputFrames;
    }
}
