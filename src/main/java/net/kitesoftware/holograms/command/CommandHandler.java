/*
 * Copyright (c) 2016-2018 Niall Lindsay
 * Email: niall_lindsay@icloud.com
 */

package net.kitesoftware.holograms.command;

import net.kitesoftware.holograms.HolographicExtension;
import net.kitesoftware.holograms.command.subs.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandHandler implements CommandExecutor, TabCompleter {

    private HolographicExtension plugin;
    private List<BaseCommand> commandList;

    public CommandHandler(HolographicExtension plugin) {
        this.plugin = plugin;
        commandList = new ArrayList<>();

        //Register subcommands
        registerCommand(new CommandHelp(this));
        registerCommand(new CommandList(this));
        registerCommand(new CommandInfo(this));
        registerCommand(new CommandAbout(this));
        registerCommand(new CommandReload(this));
    }

    public List<BaseCommand> getCommands() {
        return commandList;
    }

    public void registerCommand(BaseCommand command) {
        commandList.add(command);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (args.length == 0) {
            commandSender.sendMessage("§6§lHolographic§e§lExtension: §7Use §f/" + label + " help §7to see commands.");
            return true;
        }

        for (BaseCommand subCommand : getCommands()) {
            if (subCommand.getName().equalsIgnoreCase(args[0])) {
                if (subCommand.getMinimumArgs() <= args.length - 1) {
                    //Remove the first arg from the array so it starts at 0 for our sub commands.
                    if (commandSender.hasPermission("holographic-extension." + subCommand.getName())) {
                        return subCommand.execute(commandSender, label, Arrays.copyOfRange(args, 1, args.length));
                    } else{
                        commandSender.sendMessage("§cYou do not have the 'holographic-extension." + subCommand.getName() + "' permission to use this command.");
                    }
                }else{
                    commandSender.sendMessage("§eThe §6" + subCommand.getName() + " command requires at least " + subCommand.getMinimumArgs() + " arguments.");
                    commandSender.sendMessage("Usage: /" + label + " " + subCommand.getName() + " " + subCommand.getPossibleArgs());
                }
            }
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String label, String[] strings) {
        return null;
    }

    public HolographicExtension getPlugin() {
        return plugin;
    }
}
