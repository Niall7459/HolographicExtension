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

import org.bukkit.command.CommandSender;

public abstract class SubCommand {
    private final String name;
    private final String description;
    private final String possibleArgs;
    private final int minimumArgs;

    private final CommandHandler commandHandler;

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
