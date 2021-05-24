package me.conclure.nonamer.command;

import me.conclure.nonamer.command.parse.CommandParseFail;
import me.conclure.nonamer.command.parse.CommandParseFlag;
import me.conclure.nonamer.command.parse.CommandParseResult;
import me.conclure.nonamer.command.parse.CommandParseSuccess;
import me.conclure.nonamer.command.parse.CommandParser;
import me.conclure.nonamer.command.sender.CommandSender;

import java.util.Collections;
import java.util.Set;

public class CommandInterceptor {
  private final CommandManager commandManager;

  public CommandInterceptor(CommandManager commandManager) {
    this.commandManager = commandManager;
  }

  public void handle(CommandSender sender, String str, Set<CommandParseFlag> flags) {
    CommandParseResult parse = CommandParser.parse(str,flags);

    if (parse == null) {
      return;
    }

    if (parse instanceof CommandParseSuccess) {
      CommandParseSuccess parseSuccess = (CommandParseSuccess)parse;
      this.commandManager.coordinator().dispatch(sender,parseSuccess.name(),parseSuccess.arguments());
      return;
    }

    if (parse instanceof CommandParseFail) {
      CommandParseFail parseFail = (CommandParseFail) parse;
      switch (parseFail) {
        case EMPTY_COMMAND: {
          sender.sendMessage("EMPTY COMMAND");
        }
      }
    }
  }

  public void handle(CommandSender sender, String str) {
    this.handle(sender, str, Collections.emptySet());
  }
}
