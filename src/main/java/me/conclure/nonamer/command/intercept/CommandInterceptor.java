package me.conclure.nonamer.command.intercept;

import me.conclure.nonamer.command.CommandLine;
import me.conclure.nonamer.command.CommandManager;
import me.conclure.nonamer.command.parse.CommandParseResult;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

public class CommandInterceptor {
  private final CommandManager commandManager;

  public CommandInterceptor(CommandManager commandManager) {
    this.commandManager = commandManager;
  }

  public void handle(String str, Set<CommandInterceptFlag> flags) {
    CommandParseResult parse = this.commandManager.parser().parse(str);
    Optional<CommandLine> commandLine = parse.commandLine();
    if (commandLine.isEmpty()) {
      return;
    }
  }

  public void handle(String str) {
    this.handle(str, Collections.emptySet());
  }
}
