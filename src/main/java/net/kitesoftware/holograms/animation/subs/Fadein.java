package net.kitesoftware.holograms.animation.subs;

import net.kitesoftware.holograms.animation.BaseAnimation;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Fadein extends BaseAnimation {

    public Fadein() {
        super("fadein", "Fade text from black to white", null);
    }

    @Override
    public List<String> setAnimations(String text, Map<String, String> options) {
        return Arrays.asList(
                "§0" + text,
                "§8" + text,
                "§7" + text,
                "§f" + text
        );
    }
}
