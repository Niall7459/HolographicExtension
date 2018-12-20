/*
 * Copyright (c) 2016-2018 Niall Lindsay
 * Email: niall_lindsay@icloud.com
 */

package net.kitesoftware.holograms.placeholder;

import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.List;

public class RefreshPlaceholders {

    public void register(Plugin plugin) {

        List<String> changingTextImitation = Arrays.asList("§o §r", "§k §r");

        HologramsAPI.registerPlaceholder(plugin, "{fastest}", 0.1, new HextPlaceholderReplacer(
                changingTextImitation
        ));

        HologramsAPI.registerPlaceholder(plugin, "{fast}", 0.5, new HextPlaceholderReplacer(
                changingTextImitation
        ));

        HologramsAPI.registerPlaceholder(plugin, "{medium}", 1, new HextPlaceholderReplacer(
                changingTextImitation
        ));

        HologramsAPI.registerPlaceholder(plugin, "{slow}", 5, new HextPlaceholderReplacer(
                changingTextImitation
        ));

        HologramsAPI.registerPlaceholder(plugin, "{slowest}", 10, new HextPlaceholderReplacer(
                changingTextImitation
        ));
    }
}
