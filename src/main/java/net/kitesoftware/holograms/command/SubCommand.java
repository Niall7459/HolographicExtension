/*
 * Copyright (c) 2016-2019 Niall Lindsay
 *
 */

package net.kitesoftware.holograms.command;

import org.bukkit.command.CommandSender;

public abstract class SubCommand {

    private String name;
    private String description;
    private String possibleArgs;

    private int minimumArgs;

    private CommandHandler commandHandler;

    public SubCommand(String name, String description, String possibleArgs, int minimumArgs, CommandHandler commandHandler) {
        this.name = name;
        this.description = description;
        this.possibleArgs = possibleArgs;
        this.minimumArgs = minimumArgs;
        this.commandHandler = commandHandler;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPossibleArgs() {
        return possibleArgs;
    }

    int getMinimumArgs() {
        return minimumArgs;
    }

    protected CommandHandler getCommandHandler() {
        return commandHandler;
    }

    public abstract boolean execute(CommandSender sender, String label, String[] args);
}
