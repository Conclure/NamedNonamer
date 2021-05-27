package me.conclure.nonamer.command.commands;

import me.conclure.nonamer.command.CommandArguments;
import me.conclure.nonamer.command.sender.CommandSender;
import me.conclure.nonamer.command.sender.DiscordCommandSender;
import me.conclure.nonamer.locale.Message;

public abstract class UserCommand extends SpecializedCommand<DiscordCommandSender> {
    public UserCommand() {
        super(DiscordCommandSender.class);
    }

    @Override
    protected void executeAbnormally(CommandSender sender, CommandArguments arguments) {
        Message.ONLY_USER.send(sender);
    }
}
