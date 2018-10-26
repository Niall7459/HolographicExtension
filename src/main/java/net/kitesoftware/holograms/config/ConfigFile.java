package net.kitesoftware.holograms.config;

import net.kitesoftware.holograms.HolographicExtension;
import net.kitesoftware.holograms.util.Utils;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.List;

public class ConfigFile {

    private FileConfiguration config;

    public void reload(Plugin plugin) {
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
            Double speed = section.getDouble("speed");

            frames = Utils.setAnimations(frames);

            ConfigAnimation animation = new ConfigAnimation(animationName, speed, frames);
            HolographicExtension.getInstance().getUserAnimationManager().registerAnimation(animation);
        }
    }

    public FileConfiguration getConfig() {
        return config;
    }
}
