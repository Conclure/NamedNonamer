package me.conclure.nonamer.command;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.jetbrains.annotations.NotNull;

import me.conclure.nonamer.command.commands.Command;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;

import com.google.common.collect.ForwardingMap;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.MoreCollectors;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

public class CommandCoordinator {
  private final ExecutorService executor = Executors.newSingleThreadExecutor(new ThreadFactoryBuilder()
      .setNameFormat("command-executor")
      .setDaemon(true)
      .build());
  private final CommandMap commandMap = new CommandMap(ImmutableList.<Command>builder()

      .build());

  public void execute(CommandSender sender, String commandName, CommandLine line) {

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
