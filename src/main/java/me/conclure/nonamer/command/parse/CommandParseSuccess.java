package me.conclure.nonamer.command.parse;

import me.conclure.nonamer.command.CommandArguments;

public class CommandParseSuccess implements CommandParseResult {
  private final CommandArguments arguments;
  private final String name;

  public CommandParseSuccess(CommandArguments arguments, String name) {
    this.arguments = arguments;
    this.name = name;
  }

  public CommandArguments arguments() {
    return this.arguments;
  }

  public String name() {
    return this.name;
  }
}