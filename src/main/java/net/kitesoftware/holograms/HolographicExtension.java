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
package net.kitesoftware.holograms;

import net.kitesoftware.holograms.animation.AnimationRegistry;
import net.kitesoftware.holograms.animation.AnimationReplacer;
import net.kitesoftware.holograms.command.CommandHandler;
import net.kitesoftware.holograms.config.ConfigFile;
import net.kitesoftware.holograms.hook.ProtocolHook;
import net.kitesoftware.holograms.metrics.MetricsLite;
import net.kitesoftware.holograms.placeholder.PlaceholderRegistry;
import net.kitesoftware.holograms.updater.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class HolographicExtension extends JavaPlugin {
    private static String version;
    private PlaceholderRegistry placeholderRegistry;
    private AnimationRegistry animationRegistry;
    private ProtocolHook protocolHook;
    private ConfigFile config;

    @Override
    public void onEnable() {
        version = getDescription().getVersion();
        Bukkit.getConsoleSender().sendMessage("§e[HolographicExtension] §fStarting HolographicExtension v" + version);

        if (!Bukkit.getPluginManager().isPluginEnabled("HolographicDisplays")) {
            getLogger().warning("You need HolographicDisplays installed to use HolographicExtension.");
            getLogger().warning("Please install and try again. Disabling...");
            this.setEnabled(false);
        }

        animationRegistry = new AnimationRegistry();
        AnimationReplacer.setAnimationRegistry(animationRegistry);

        CommandHandler commandHandler = new CommandHandler(this);
        getCommand("hext").setExecutor(commandHandler);
        getCommand("hext").setTabCompleter(commandHandler);

        saveDefaultConfig();
        placeholderRegistry = new PlaceholderRegistry(this);
        config = new ConfigFile(this);
        config.reload();

        hookProtocolLib();
        new MetricsLite(this, 18461);

        if (!getConfig().getBoolean("enable-update-check")) return;
        // Disable update checking on this build as it's not on Spigot, causing unnecessary 'outdated' messages
        if(getDescription().getVersion().equals("1.10.10")) return;

        UpdateChecker updateChecker = new UpdateChecker(this, 18461);
        updateChecker.checkUpdates().whenComplete((status, error) -> {
            if (status == UpdateChecker.UpdateStatus.DIFFERENT_VERSION) {
                Bukkit.getConsoleSender().sendMessage("§e[HolographicExtension] §fA new update is available for download at https://www.spigotmc.org/resources/holographicextension.18461/");
            } else if (status == UpdateChecker.UpdateStatus.UNAVAILABLE) {
                Bukkit.getConsoleSender().sendMessage("§e[HolographicExtension] §fUpdate checking is unavailable at this time.");
            }
        });
    }

    public void hookProtocolLib() {
        if (Bukkit.getPluginManager().isPluginEnabled("ProtocolLib") && Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            protocolHook = new ProtocolHook();
            protocolHook.enable(this);
        } else {
            Bukkit.getConsoleSender().sendMessage("§e[HolographicExtension] §fIf you want to use the placeholder extension, please install ProtocolLib and PlaceholderAPI");
        }
    }

    public ProtocolHook getProtocolHook() {
        return protocolHook;
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§e[HolographicExtension] §fDisabling HolographicExtension v" + version);
    }

    public AnimationRegistry getAnimationRegistry() {
        return animationRegistry;
    }

    public PlaceholderRegistry getPlaceholderRegistry() {
        return placeholderRegistry;
    }

    public ConfigFile getConfigManager() {
        return config;
    }
}
