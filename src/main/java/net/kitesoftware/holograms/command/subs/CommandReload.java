package net.kitesoftware.holograms.command.subs;

import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import net.kitesoftware.holograms.HolographicExtension;
import net.kitesoftware.holograms.command.BaseCommand;
import net.kitesoftware.holograms.placeholder.RefreshPlaceholders;
import org.bukkit.command.CommandSender;

public class CommandReload extends BaseCommand {

    public CommandReload() {
        super("reload", "Reload configuration files", "", 0);
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        HolographicExtension instance = HolographicExtension.getInstance();
        HologramsAPI.unregisterPlaceholders(instance);

        new RefreshPlaceholders().register(instance);
        instance.getUserAnimationManager().getRegisteredAnimations().clear();
        instance.getConfigManager().reload(instance);

        if (instance.getProtocolHook() != null) {
            instance.getProtocolHook().disable();
            instance.hookProtocolLib();
        }

        sender.sendMessage("Configuration files have been reloaded");
        return true;
    }
}
