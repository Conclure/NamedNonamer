package me.conclure.nonamer.command.commands;

import me.conclure.nonamer.command.CommandArguments;
import me.conclure.nonamer.command.sender.CommandSender;

public abstract class SpecializedCommand<S extends CommandSender> extends Command {
    private final Class<S> type;

    public SpecializedCommand(Class<S> type) {
        this.type = type;
    }

    protected abstract void executeSuccessfully(S sender, CommandArguments arguments) throws InterruptedException;

    protected void executeAbnormally(CommandSender sender, CommandArguments arguments) { }

    public abstract String name();

    @Override
    public final void execute(CommandSender sender, CommandArguments arguments) throws InterruptedException {
        if (type.isInstance(sender)) {
            this.executeSuccessfully(type.cast(sender),arguments);
            return;
        }

        this.executeAbnormally(sender,arguments);
    }
}
