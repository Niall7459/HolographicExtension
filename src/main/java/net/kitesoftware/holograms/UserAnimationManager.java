/*
 * Copyright (c) 2016-2018 Niall Lindsay
 * Email: niall_lindsay@icloud.com
 */

package net.kitesoftware.holograms;

import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import net.kitesoftware.holograms.config.ConfigAnimation;
import net.kitesoftware.holograms.exception.AnimationNotFoundException;
import net.kitesoftware.holograms.placeholder.HextPlaceholderReplacer;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

public class UserAnimationManager {

    private static final String PREFIX = "ext:";
    private List<ConfigAnimation> animationList;

    private HolographicExtension plugin;

    UserAnimationManager(HolographicExtension plugin) {
        animationList = new ArrayList<>();
        this.plugin = plugin;
    }

    public void registerAnimation(ConfigAnimation animation) {
        animationList.add(animation);

        Bukkit.getConsoleSender().sendMessage("§e[HolographicExtension] §7Registering animation " + animation.getName() + " from config.");
        String placeholder = "{" + PREFIX + animation.getName() + "}";
        HologramsAPI.registerPlaceholder(plugin, placeholder, animation.getRefreshRate(), new HextPlaceholderReplacer(animation.getFrames()));
    }

    public List<ConfigAnimation> getRegisteredAnimations() {
        return animationList;
    }

    public ConfigAnimation getAnimationFromName(String name) throws AnimationNotFoundException {
        for (ConfigAnimation animation : animationList) {
            if (animation.getName().equalsIgnoreCase(name)) return animation;
        }
        throw new AnimationNotFoundException();
    }
}
