package me.conclure.nonamer.command.commands;

import me.conclure.nonamer.bootstrap.Bootstrap;
import me.conclure.nonamer.bot.Bot;
import me.conclure.nonamer.command.CommandArguments;
import me.conclure.nonamer.command.CommandManager;
import me.conclure.nonamer.command.sender.ConsoleCommandSender;

public class StopCommand extends ConsoleCommand {
    private final Bootstrap bootstrap;

    public StopCommand(Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    @Override
    protected void executeSuccessfully(ConsoleCommandSender sender, CommandArguments arguments) {
        this.bootstrap.disable();
    }

    @Override
    public String name() {
        return "stop";
    }
}
