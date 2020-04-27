/*
 *  Holographic Extension
 *  Copyright (C) 2015 - 2019 Niall7459
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package net.kitesoftware.holograms.config;

import net.kitesoftware.holograms.HolographicExtension;
import net.kitesoftware.holograms.animation.AnimationReplacer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.List;

public class ConfigFile {
    private FileConfiguration config;
    private final HolographicExtension plugin;

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
            frames = AnimationReplacer.setAnimations(frames);

            ConfigAnimation animation = new ConfigAnimation(animationName, speed, frames);
            plugin.getPlaceholderRegistry().registerPlaceholder(animation);
        }
    }
}
