package me.conclure.nonamer.command;

import me.conclure.nonamer.command.parse.CommandParseResult;
import me.conclure.nonamer.command.parse.CommandParser;

import java.util.Optional;

public class CommandInterceptor {
  private final CommandParser parser;

  public CommandInterceptor() {
    this.parser = new CommandParser();
  }

  public void handle(String str) {
    CommandParseResult parse = this.parser.parse(str);
    Optional<CommandLine> commandLine = parse.commandLine();
    if (commandLine.isEmpty()) {
      return;
    }


  }
}
