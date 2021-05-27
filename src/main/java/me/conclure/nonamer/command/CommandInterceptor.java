package me.conclure.nonamer.command;

import me.conclure.nonamer.command.parse.CommandParseFail;
import me.conclure.nonamer.command.parse.CommandParseFlag;
import me.conclure.nonamer.command.parse.CommandParseResult;
import me.conclure.nonamer.command.parse.CommandParseSuccess;
import me.conclure.nonamer.command.sender.CommandSender;
import me.conclure.nonamer.util.logging.Logger;
import me.conclure.nonamer.util.logging.LoggerCreator;

import java.util.Collections;
import java.util.Set;

public class CommandInterceptor {
  private final CommandManager commandManager;
  private final Logger logger = LoggerCreator.create("command-interception");

  public CommandInterceptor(CommandManager commandManager) {
    this.commandManager = commandManager;
  }

  public void handle(CommandSender sender, String str, Set<CommandParseFlag> flags) {
    CommandParseResult parse = this.commandManager.parser().parse(str,flags);

    if (parse == null) {
      return;
    }

    if (parse instanceof CommandParseSuccess) {
      CommandParseSuccess parseSuccess = (CommandParseSuccess)parse;
      sender.name().thenAccept(name -> {
        CommandArguments commandArguments = parseSuccess.arguments();
        String commandName = parseSuccess.name();
        this.logger.infof("%s issued: %s %s",name, commandName, commandArguments);
        this.commandManager.coordinator().dispatch(sender, commandName, commandArguments);
      });
      return;
    }

    if (parse instanceof CommandParseFail) {
      CommandParseFail parseFail = (CommandParseFail) parse;
      switch (parseFail) {
        case EMPTY_COMMAND: {
        }
      }
    }
  }

  public void handle(CommandSender sender, String str) {
    this.handle(sender, str, Collections.emptySet());
  }
}
