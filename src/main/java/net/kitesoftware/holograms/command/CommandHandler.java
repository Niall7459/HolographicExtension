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
package net.kitesoftware.holograms.command;

import net.kitesoftware.holograms.HolographicExtension;
import net.kitesoftware.holograms.command.subs.*;
import net.kitesoftware.holograms.config.ConfigAnimation;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CommandHandler implements CommandExecutor, TabCompleter {
    private final HolographicExtension plugin;
    private final List<SubCommand> commandList = new ArrayList<>();

    public CommandHandler(HolographicExtension plugin) {
        this.plugin = plugin;

        registerCommand(new CommandHelp(this));
        registerCommand(new CommandList(this));
        registerCommand(new CommandInfo(this));
        registerCommand(new CommandAbout(this));
        registerCommand(new CommandReload(this));
    }

    public Collection<SubCommand> getCommands() {
        return commandList;
    }

    private void registerCommand(SubCommand command) {
        commandList.add(command);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (args.length == 0) {
            commandSender.sendMessage("§6§lHolographic§e§lExtension: §7Use §f/" + label + " help §7to see commands.");
            return true;
        }

        for (SubCommand subCommand : getCommands()) {
            if (subCommand.getName().equalsIgnoreCase(args[0])) {
                if (subCommand.getMinimumArgs() <= args.length - 1) {
                    if (commandSender.hasPermission("holographic-extension." + subCommand.getName())) {
                        return subCommand.execute(commandSender, label, Arrays.copyOfRange(args, 1, args.length));
                    } else {
                        commandSender.sendMessage("§cYou do not have the 'holographic-extension." + subCommand.getName() + "' permission to use this command.");
                    }
                } else {
                    commandSender.sendMessage("§eThe §6" + subCommand.getName() + " command requires at least " + subCommand.getMinimumArgs() + " arguments.");
                    commandSender.sendMessage("Usage: /" + label + " " + subCommand.getName() + " " + subCommand.getPossibleArgs());
                }
            }
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String label, String[] args) {
        List<String> completions = new ArrayList<>();

        for (SubCommand subCommand : commandList) {
            if (args.length > 1) {
                if (args[0].equalsIgnoreCase("info") && subCommand.getName().equalsIgnoreCase("info")) {
                    if (args.length == 2) {
                        for (ConfigAnimation animation : plugin.getPlaceholderRegistry().getRegisteredPlaceholders()) {
                            if (animation.getName().startsWith(args[1])) {
                                completions.add(animation.getName());
                            }
                        }
                        return completions;
                    } else if (args.length == 3) {
                        if (args[2].length() == 0 || "true".startsWith(args[2])) completions.add("true");
                        if (args[2].length() == 0 || "false".startsWith(args[2])) completions.add("false");
                        return completions;
                    }
                }
            } else {
                if (subCommand.getName().startsWith(args[0])) {
                    completions.add(subCommand.getName());
                }
            }
        }

        return completions;
    }

    public HolographicExtension getPlugin() {
        return plugin;
    }
}
