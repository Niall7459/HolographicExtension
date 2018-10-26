package net.kitesoftware.holograms.animation.subs;

import net.kitesoftware.holograms.animation.BaseAnimation;
import net.kitesoftware.holograms.util.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Typewriter extends BaseAnimation {

    public Typewriter() {
        super("typewriter", "Creates a typing effect animation.", new HashMap<String, String>() {{
            put("cursor", "_");
            put("pause", "10");
            put("reverse", "true");
        }});
    }

    @Override
    public List<String> setAnimations(String text, Map<String, String> options) {
        List<String> frames = new ArrayList<>();

        options = Utils.mergeMap(getOptions(), options);

        String cursor = options.get("cursor");
        int pauseTime = Integer.parseInt(options.get("pause"));
        boolean reverse = Boolean.parseBoolean(options.get("reverse"));

        StringBuilder current = new StringBuilder();
        int counter = 0;

        for (int i = 0; i < pauseTime; i++) {
            frames.add("");
            frames.add("");
            frames.add(cursor);
            frames.add(cursor);
        }

        for (char character : text.toCharArray()) {
            current.append(character);

            if (counter < 2) {
                frames.add(current + "");
            }else{
                frames.add(current + cursor);
            }

            if (counter == 4) {
                counter = 0;
            } else {
                counter += 1;
            }

        }

        for (int i = 0; i < pauseTime; i++) {
            if (counter < 2) {
                frames.add(current + "");
            }else{
                frames.add(current + cursor);
            }

            if (counter == 4) {
                counter = 0;
            } else {
                counter += 1;
            }
        }

        if (reverse) {
            for (int i = text.length(); i > 0; i--) {
                current.deleteCharAt(i-1);
                if (counter < 2) {
                    frames.add(current + "");
                }else{
                    frames.add(current + cursor);
                }

                if (counter == 4) {
                    counter = 0;
                } else {
                    counter += 1;
                }
            }
        }

        return frames;
    }
}
