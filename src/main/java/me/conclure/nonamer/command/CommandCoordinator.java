package me.conclure.nonamer.command;

import me.conclure.nonamer.bootstrap.Bootstrap;
import me.conclure.nonamer.command.commands.Command;
import me.conclure.nonamer.command.commands.CommandException;
import me.conclure.nonamer.command.commands.HelpCommand;
import me.conclure.nonamer.command.commands.StopCommand;
import me.conclure.nonamer.command.parse.CommandParser;
import me.conclure.nonamer.command.sender.CommandSender;
import me.conclure.nonamer.locale.Message;
import me.conclure.nonamer.util.logging.Logger;
import me.conclure.nonamer.util.logging.LoggerCreator;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Function;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

public class CommandCoordinator {
  private final ExecutorService executor = Executors.newSingleThreadExecutor(new ThreadFactoryBuilder()
      .setNameFormat("Command Executor")
      .setDaemon(true)
      .build());
  private final Logger logger = LoggerCreator.create(this);
  private final CommandMap commandMap;

  public CommandCoordinator(Bootstrap bootstrap) {
    this.commandMap = new CommandMap(ImmutableList.<Command>builder()
            .add(new HelpCommand())
            .add(new StopCommand(bootstrap))
            .build());
    this.executor.execute(() -> {
      try {
        bootstrap.enableProcess().await();
      } catch (InterruptedException e) {
        bootstrap.logger().error(e);
      }
    });
  }

  public void dispatch(CommandSender sender, String commandName, CommandArguments line) {
    Optional<Command> optional = this.commandMap.get(commandName);

    if (optional.isEmpty()) {
      Message.UNKNOWN_COMMAND.send(sender, CommandParser.PREFIX, sender);
      return;
    }

    Command command = optional.get();

    this.executor.execute(() -> {
      try {
        command.execute(sender,line);
      } catch (Exception exception) {
        this.logger.error(new CommandException(exception));
      }
    });
  }

  public Executor executor() {
    return this.executor;
  }

  static class CommandMap {
    private final Map<String,Command> backingMap;

    private CommandMap(Collection<Command> commands) {
      this.backingMap = commands.stream()
          .collect(ImmutableMap.toImmutableMap(cmd -> cmd.name().toLowerCase(), Function.identity()));
    }

    public Optional<Command> get(String commandName) {
      return Optional.ofNullable(this.backingMap.get(commandName.toLowerCase()));
    }
  }
}
