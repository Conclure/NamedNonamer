package me.conclure.nonamer.command;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import me.conclure.nonamer.bot.Bot;
import me.conclure.nonamer.command.parse.CommandParseFail;
import me.conclure.nonamer.command.parse.CommandParseFlag;
import me.conclure.nonamer.command.parse.CommandParseResult;
import me.conclure.nonamer.command.parse.CommandParseSuccess;
import me.conclure.nonamer.command.sender.CommandSender;
import me.conclure.nonamer.util.logging.Logger;
import me.conclure.nonamer.util.logging.LoggerCreator;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CommandInterceptor {
  private final ExecutorService executor = Executors.newSingleThreadExecutor(new ThreadFactoryBuilder()
          .setNameFormat("Command Executor")
          .setDaemon(true)
          .build());
  private final Logger logger = LoggerCreator.create("command-interception");
  private final Bot bot;

  public CommandInterceptor(Bot bot) {
    this.executor.execute(() -> {
      try {
        bot.bootstrap().enableProcess().await();
      } catch (InterruptedException e) {
        logger.error(e);
      }
    });
    this.bot = bot;
  }

  public void handle(CommandSender sender, String str, Set<CommandParseFlag> flags) {
    this.executor.execute(() -> {
      CommandParseResult parse = this.bot.commandManager().parser().parse(str,flags);

      if (parse == null) {
        return;
      }

      String senderName = sender.name().join();

      if (parse instanceof CommandParseSuccess) {
        CommandParseSuccess parseSuccess = (CommandParseSuccess)parse;
        CommandArguments commandArguments = parseSuccess.arguments();
        String commandName = parseSuccess.name();
        this.logger.infof("%s issued: %s %s",senderName, commandName, commandArguments);
        this.bot.commandManager().coordinator().dispatch(this.executor, sender, commandName, commandArguments);
        return;
      }

      if (parse instanceof CommandParseFail) {
        CommandParseFail parseFail = (CommandParseFail) parse;
        switch (parseFail) {
          case EMPTY_COMMAND: {
            this.logger.infof("%s issued empty command.",senderName);
          }
        }
      }
    });
  }

  public void handle(CommandSender sender, String str) {
    this.handle(sender, str, Collections.emptySet());
  }
}
