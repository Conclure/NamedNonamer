package me.conclure.nonamer.command.commands;

import me.conclure.nonamer.command.CommandArguments;
import me.conclure.nonamer.command.sender.CommandSender;

public class HelpCommand extends Command {
    @Override
    public String name() {
        return "help";
    }

    @Override
    public void execute(CommandSender sender, CommandArguments arguments) {
        sender.sendMessage("help");
    }
}
