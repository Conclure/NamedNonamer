package me.conclure.nonamer.command.parse;

import me.conclure.nonamer.command.CommandArguments;

import java.util.Arrays;
import java.util.Set;

import com.google.errorprone.annotations.Var;

public class CommandParser {
  static final String PREFIX = "!";


  public static CommandParseResult parse(@Var String string, Set<CommandParseFlag> flags) {
    string = string.strip();

    if (flags.contains(CommandParseFlag.ASSERT_PREFIX)) {
      if (string.isBlank()) {
        return null;
      }

      if (!string.startsWith(PREFIX)) {
        return null;
      }

      string = string.substring(1);
    }

    if (string.isBlank()) {
      return CommandParseFail.EMPTY_COMMAND;
    }

    String[] line = string.split(" ");
    String name = line[0].toLowerCase();
    String[] args = Arrays.copyOfRange(line,1,line.length);

    return new CommandParseSuccess(new CommandArguments(args),name);
  }
}