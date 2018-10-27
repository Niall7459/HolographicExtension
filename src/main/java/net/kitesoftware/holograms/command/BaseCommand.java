/*
 * Copyright (c) 2016-2018 Niall Lindsay
 * Email: niall_lindsay@icloud.com
 */

package net.kitesoftware.holograms.command;

import org.bukkit.command.CommandSender;

public abstract class BaseCommand {

    private String name;
    private String description;
    private String possibleArgs;

    private int minimumArgs;

    public BaseCommand(String name, String description, String possibleArgs, int minimumArgs) {
        this.name = name;
        this.description = description;
        this.possibleArgs = possibleArgs;
        this.minimumArgs = minimumArgs;
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

    public abstract boolean execute(CommandSender sender, String label, String[] args);
}
