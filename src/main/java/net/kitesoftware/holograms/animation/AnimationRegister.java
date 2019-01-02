/*
 * Copyright (c) 2016-2018 Niall Lindsay
 * Email: niall_lindsay@icloud.com
 */

package net.kitesoftware.holograms.animation;

import net.kitesoftware.holograms.animation.iface.Animation;
import net.kitesoftware.holograms.animation.iface.ConfigurableAnimation;
import net.kitesoftware.holograms.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

public class AnimationRegister {

    private List<Animation> registeredAnimations;

    public AnimationRegister() {
        registeredAnimations = new ArrayList<>();
        registerDefaultAnimations();
    }

    private void registerDefaultAnimations() {
        for (DefaultAnimation anim : DefaultAnimation.values()) {
            registerAnimation(anim.getAnimation());
        }
    }

    /**
     * Register an custom animation.
     * @param animation
     */
    public void registerAnimation(Animation animation) {
        registeredAnimations.add(animation);
    }

    public List<String> setAnimations(String text) {
        List<String> frames = new ArrayList<>();

        for (Animation animation : registeredAnimations) {
            Matcher matcher = animation.getMatcher(text);

            if (matcher.find()) {
                List<String> animationFrames;

                String value = matcher.group(4);
                String options = (matcher.group(2).length() > 0 ? matcher.group(2).substring(1) : matcher.group(2));

                if (animation instanceof ConfigurableAnimation) {
                    ConfigurableAnimation configAnimation = (ConfigurableAnimation) animation;

                    Map<String, String> optionsMap = Utils.mergeMap(configAnimation.getOptions(), Utils.decodeOptions(options));
                    animationFrames = ((ConfigurableAnimation) animation).create(value, optionsMap);
                } else {
                    animationFrames = animation.create(value);
                }

                if (animationFrames != null) {
                    String before = matcher.group(1);
                    String after = matcher.group(6);

                    frames.addAll(Utils.insertList(before, animationFrames, after));
                }
            }
        }

        if (frames.size() < 1) {
            frames.add(text);
        }

        return frames;
    }
}
