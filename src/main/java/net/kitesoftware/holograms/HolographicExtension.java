/*
 * Copyright (c) 2016-2018 Niall Lindsay
 * Email: niall_lindsay@icloud.com
 */

package net.kitesoftware.holograms;

import net.kitesoftware.holograms.animation.AnimationRegister;
import net.kitesoftware.holograms.command.CommandHandler;
import net.kitesoftware.holograms.config.ConfigFile;
import net.kitesoftware.holograms.metrics.Metrics;
import net.kitesoftware.holograms.placeholder.RefreshPlaceholders;
import net.kitesoftware.holograms.updater.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public class HolographicExtension extends JavaPlugin {

    private static HolographicExtension instance;
    private AnimationRegister animationRegister;
    private ProtocolHook protocolHook;
    private ConfigFile config;
    private UserAnimationManager userAnimationManager;
    private UpdateChecker updateChecker;

    private static String version;

    @Override
    public void onEnable() {
        instance = this;
        version = getDescription().getVersion();

        Bukkit.getConsoleSender().sendMessage("§e[HolographicExtension] §fStarting HolographicExtension v" + version);

        CommandHandler commandHandler = new CommandHandler();

        if (!Bukkit.getPluginManager().isPluginEnabled("HolographicDisplays")) {
            getLogger().warning("You need HolographicDisplays installed to use HolographicExtension.");
            getLogger().warning("Please install and try again. Disabling...");
            this.setEnabled(false);
        }

        animationRegister = new AnimationRegister();

        getCommand("hext").setExecutor(commandHandler);
        getCommand("hext").setTabCompleter(commandHandler);

        userAnimationManager = new UserAnimationManager();
        config = new ConfigFile();
        config.reload(this);

        hookProtocolLib();

        updateChecker = new UpdateChecker(this, 18461);
        UpdateChecker.UpdateStatus status = updateChecker.checkForUpdate();
        if (status.equals(UpdateChecker.UpdateStatus.MISMATCH)) {
            Bukkit.getConsoleSender().sendMessage("§e[HolographicExtension] §fA new update is available for download at https://www.spigotmc.org/resources/holographicextension.18461/");
        } else if (status.equals(UpdateChecker.UpdateStatus.UNAVAILABLE)){
            Bukkit.getConsoleSender().sendMessage("§e[HolographicExtension] §fUpdate checking is unavailable at this time.");
        }

        new Metrics(this);
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

    public static HolographicExtension getInstance() {
        return instance;
    }

    public static String getVersion() {
        return version;
    }

    public AnimationRegister getAnimationRegister() {
        return animationRegister;
    }

    public UserAnimationManager getUserAnimationManager() {
        return userAnimationManager;
    }

    public ConfigFile getConfigManager() {
        return config;
    }
}
