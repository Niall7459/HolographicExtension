package net.kitesoftware.holograms.animation.subs;

import net.kitesoftware.holograms.animation.BaseAnimation;
import net.kitesoftware.holograms.util.Utils;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Glow extends BaseAnimation {

    public Glow() {
        super("glow", "Make a text glow certain colors", new HashMap<String, String>() {{
            //Default glow options.
            put("normal", "§7§l");
            put("start","§d§l");
            put("middle","§5§l");
            put("end","§d§l");
            put("size","5");
            put("pause","10");
        }});
    }

    @Override
    public List<String> setAnimations(String text, Map<String, String> options) {

        ArrayList<String> frames = new ArrayList<>();
        options = Utils.mergeMap(getOptions(), options);

        String normalFormat = options.get("normal");
        String startFormat =  options.get("start");
        String middleFormat = options.get("middle");
        String endFormat =    options.get("end");

        int size = Integer.parseInt(options.get("size"));
        int pause = Integer.parseInt(options.get("pause"));

        int length = text.length();
        int iterations = length + size + 2 + 1;

        int startsub = 0;
        int endsub = 0;

        int counter = 0;

        for (int pos = 0; pos < iterations; ++pos) {
            if (pos > 1 && pos < iterations - 2) {
                if (counter >= length - size) {
                    startsub -= 1;
                    endsub += 1;
                } else {
                    if (startsub + 1 > size) {
                        startsub = size;
                        counter += 1;
                    } else {
                        startsub += 1;
                    }
                }

            }

            String startPart = "", middlePart = "", endPart = "", before = "", last = "";

            if (pos >= iterations - length - 1 && pos < iterations - 1) {
                startPart = text.substring(pos - (iterations - length - 1), pos - (iterations - length) + 2);
            }

            if (pos > 1 && pos < iterations - 2) {
                middlePart = text.substring(pos - 1 - startsub - endsub, pos - 1 - endsub);
            }

            if(pos > 0 && pos <= length) {
                endPart = text.substring(pos - 1, pos);
            }

            if(pos < length) {
                last = text.substring(pos);
            }

            if(pos >= iterations - length) {
                before = text.substring(0, pos + 1 - (iterations - length));
            }

            before = normalFormat + before;
            startPart = startFormat + startPart;
            middlePart = middleFormat + middlePart;
            endPart = endFormat + endPart;
            last = normalFormat + last;

            String frame = before + startPart + middlePart + endPart + last;
            frames.add(frame);
        }

        for (int i = 0; i < pause; i++) {
            frames.add(normalFormat + text);
        }

        return frames;
    }
}
