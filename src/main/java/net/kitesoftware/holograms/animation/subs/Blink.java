package net.kitesoftware.holograms.animation.subs;

import net.kitesoftware.holograms.animation.BaseAnimation;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Blink extends BaseAnimation {

    public Blink() {
        super("blink", "Creates blinking text", null);
    }

    @Override
    public List<String> setAnimations(String text, Map<String, String> options) {
        return Arrays.asList(text, "");
    }
}
