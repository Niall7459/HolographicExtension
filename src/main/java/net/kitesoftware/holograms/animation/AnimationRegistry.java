/*
 * Copyright (c) 2016-2019 Niall Lindsay
 *
 */

package net.kitesoftware.holograms.animation;

import net.kitesoftware.holograms.animation.iface.Animation;
import net.kitesoftware.holograms.animation.iface.ConfigurableAnimation;
import net.kitesoftware.holograms.util.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnimationRegistry {

    private static final Pattern ANIMATION_PATTERN = Pattern.compile("(.*)<([^ ]*)(.*?)>(.*?)</(.*?)>(.*)");
    private List<Animation> registeredAnimations;

    public AnimationRegistry() {
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
     *
     * @param animation
     */
    public void registerAnimation(Animation animation) {
        registeredAnimations.add(animation);
    }

    public List<String> setAnimations(String text) {
        Matcher matcher = ANIMATION_PATTERN.matcher(text);

        if (matcher.find()) {
            String tagName = matcher.group(2);
            Animation animation = fromName(tagName);

            if (animation != null) {
                List<String> animationFrames = new ArrayList<>();
                String options = matcher.group(3).trim();
                String value = matcher.group(4);

                if (animation instanceof ConfigurableAnimation) {
                    ConfigurableAnimation configurableAnimation = (ConfigurableAnimation) animation;
                    Map<String, String> optionsMap = Utils.mergeMap(configurableAnimation.getOptions(), Utils.decodeOptions(options));
                    animationFrames.addAll(configurableAnimation.create(value, optionsMap));
                } else {
                    animationFrames.addAll(animation.create(value));
                }

                if (animationFrames.size() > 0) {
                    String before = matcher.group(1);
                    String after = matcher.group(6);

                    animationFrames = Utils.insertList(before, animationFrames, after);
                }

                return animationFrames;
            }
        }


        return Collections.singletonList(text);
    }

    private Animation fromName(String name) {
        for (Animation animation : registeredAnimations) {
            if (animation.getName().equalsIgnoreCase(name)) return animation;
        }

        return null;
    }
}
