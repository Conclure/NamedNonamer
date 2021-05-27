package me.conclure.nonamer.command.commands;

import me.conclure.nonamer.bot.Bot;
import me.conclure.nonamer.command.CommandArguments;
import me.conclure.nonamer.command.sender.CommandSender;
import me.conclure.nonamer.command.sender.ConsoleCommandSender;
import me.conclure.nonamer.locale.Message;

public abstract class ConsoleCommand extends SpecializedCommand<ConsoleCommandSender> {
    public ConsoleCommand() {
        super(ConsoleCommandSender.class);
    }

    @Override
    protected void executeAbnormally(CommandSender sender, CommandArguments arguments) {
        Message.ONLY_CONSOLE.send(sender);
    }
}
