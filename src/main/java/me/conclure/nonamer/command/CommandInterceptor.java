package me.conclure.nonamer.command;

import me.conclure.nonamer.command.parse.CommandParseFlag;
import me.conclure.nonamer.command.parse.CommandParseResult;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

public class CommandInterceptor {
  private final CommandManager commandManager;

  public CommandInterceptor(CommandManager commandManager) {
    this.commandManager = commandManager;
  }

  public void handle(CommandSender sender, String str, Set<CommandParseFlag> flags) {
    CommandParseResult parse = this.commandManager.parser().parse(str);
    Optional<CommandLine> commandLine = parse.commandLine();
    if (commandLine.isEmpty()) {
      return;
    }

  }

  public void handle(CommandSender sender, String str) {
    this.handle(sender, str, Collections.emptySet());
  }
}
