/*
 * Copyright (c) 2016-2018 Niall Lindsay
 * Email: niall_lindsay@icloud.com
 */

package net.kitesoftware.holograms.animation;

import net.kitesoftware.holograms.animation.subs.*;
import net.kitesoftware.holograms.util.Utils;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnimationRegister {

    private List<BaseAnimation> animationList;

    public AnimationRegister() {
        animationList = new ArrayList<>();

        registerAnimation(new Blink());
        registerAnimation(new Typewriter());
        registerAnimation(new Pulse());
        registerAnimation(new Glow());
        registerAnimation(new Scroller());
        registerAnimation(new Rainbow());
        registerAnimation(new Pause());
        registerAnimation(new Left());
        registerAnimation(new Right());
        registerAnimation(new Fadein());
        registerAnimation(new Fadeout());
    }

    public List<String> setAnimations(String text) {
        List<String> frames = new ArrayList<>();
        frames.add(text);

        for (BaseAnimation animation : getAnimations()) {
            String regex = "(.*?)(<NAME(.*?)+>)(.*?)(</NAME+>)(.*?)$".replace("NAME", animation.getName());

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(text);

            if (matcher.find()) {
                frames.remove(0);

                String options = matcher.group(2);
                String value = matcher.group(4);

                HashMap<String, String> optionsMap = Utils.decodeOptions(options);
                List<String> animationFrames = animation.setAnimations(value, optionsMap);

                if (animationFrames == null) {
                    frames.add("Invalid animation configuration.");
                    Bukkit.getConsoleSender().sendMessage("§e[HolographicExtension] §cInvalid animation string detected for: " + text);
                } else {
                    String before = matcher.group(1);
                    String after = matcher.group(6);

                    frames.addAll(Utils.insertList(before, animationFrames, after));
                }
            }
        }

        return frames;
    }

    private void registerAnimation(BaseAnimation animation) {
        animationList.add(animation);
    }

    private List<BaseAnimation> getAnimations() {
        return animationList;
    }
}
