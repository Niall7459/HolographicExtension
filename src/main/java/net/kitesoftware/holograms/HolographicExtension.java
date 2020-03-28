/*
 * Copyright (c) 2016-2019 Niall Lindsay
 *
 */

package net.kitesoftware.holograms;

import net.kitesoftware.holograms.animation.AnimationRegistry;
import net.kitesoftware.holograms.command.CommandHandler;
import net.kitesoftware.holograms.config.ConfigFile;
import net.kitesoftware.holograms.placeholder.RefreshPlaceholders;
import net.kitesoftware.holograms.updater.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class HolographicExtension extends JavaPlugin {
    private static String version;
    private AnimationRegistry animationRegistry;
    private ProtocolHook protocolHook;
    private ConfigFile config;
    private UserAnimationManager userAnimationManager;

    @Override
    public void onEnable() {
        version = getDescription().getVersion();

        Bukkit.getConsoleSender().sendMessage("§e[HolographicExtension] §fStarting HolographicExtension v" + version);

        CommandHandler commandHandler = new CommandHandler(this);
        if (!Bukkit.getPluginManager().isPluginEnabled("HolographicDisplays")) {
            getLogger().warning("You need HolographicDisplays installed to use HolographicExtension.");
            getLogger().warning("Please install and try again. Disabling...");
            this.setEnabled(false);
        }

        animationRegistry = new AnimationRegistry();

        getCommand("hext").setExecutor(commandHandler);
        getCommand("hext").setTabCompleter(commandHandler);

        saveDefaultConfig();
        userAnimationManager = new UserAnimationManager(this);
        config = new ConfigFile(this);
        config.reload();

        hookProtocolLib();

        if (!getConfig().getBoolean("enable-update-check")) return;

        UpdateChecker updateChecker = new UpdateChecker(this, 18461);
        updateChecker.checkUpdates().whenComplete((status, error) -> {
            if (status.equals(UpdateChecker.UpdateStatus.DIFFERENT_VERSION)) {
                Bukkit.getConsoleSender().sendMessage("§e[HolographicExtension] §fA new update is available for download at https://www.spigotmc.org/resources/holographicextension.18461/");
            } else if (status.equals(UpdateChecker.UpdateStatus.UNAVAILABLE)) {
                Bukkit.getConsoleSender().sendMessage("§e[HolographicExtension] §fUpdate checking is unavailable at this time.");
            }
        });
    }

    public void hookProtocolLib() {
        if (Bukkit.getPluginManager().isPluginEnabled("ProtocolLib") && Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            protocolHook = new ProtocolHook();
            protocolHook.hook(this);
            new RefreshPlaceholders().register(this);
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

    public UserAnimationManager getUserAnimationManager() {
        return userAnimationManager;
    }

    public ConfigFile getConfigManager() {
        return config;
    }
}
