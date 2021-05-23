package me.conclure.nonamer.command.commands;

import me.conclure.nonamer.command.CommandArguments;
import me.conclure.nonamer.command.CommandSender;

public abstract class Command {
  public abstract String name();

  public abstract void execute(CommandSender sender, CommandArguments arguments);
}
