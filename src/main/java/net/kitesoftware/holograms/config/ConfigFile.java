/*
 * Copyright (c) 2016-2019 Niall Lindsay
 *
 */

package net.kitesoftware.holograms.config;

import net.kitesoftware.holograms.HolographicExtension;
import net.kitesoftware.holograms.util.Utils;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.List;

public class ConfigFile {

    private FileConfiguration config;
    private HolographicExtension plugin;

    public ConfigFile(HolographicExtension plugin) {
        this.plugin = plugin;
    }

    public void reload() {
        File configFile = new File(plugin.getDataFolder(), "animations.yml");

        if (!configFile.exists()) {
            plugin.getDataFolder().mkdirs();
            plugin.saveResource("animations.yml", true);
        }
        config = YamlConfiguration.loadConfiguration(configFile);
        loadAnimations();
    }

    private void loadAnimations() {
        for (String animationName : config.getKeys(false)) {
            ConfigurationSection section = config.getConfigurationSection(animationName);

            List<String> frames = section.getStringList("frames");
            double speed = section.getDouble("speed");

            frames = Utils.setAnimations(frames, plugin.getAnimationRegistry());

            ConfigAnimation animation = new ConfigAnimation(animationName, speed, frames);
            plugin.getUserAnimationManager().registerAnimation(animation);
        }
    }
}
