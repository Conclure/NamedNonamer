package me.conclure.nonamer.command.commands;

import me.conclure.nonamer.command.CommandArguments;
import me.conclure.nonamer.command.sender.CommandSender;

public abstract class Command {
  public abstract String name();

  public abstract void execute(CommandSender sender, CommandArguments arguments) throws Exception;
}
