package me.conclure.nonamer.command;

import net.dv8tion.jda.api.JDA;

import me.conclure.nonamer.bootstrap.Bootstrap;
import me.conclure.nonamer.command.parse.CommandParser;
import me.conclure.nonamer.command.listener.ConsoleCommandListener;
import me.conclure.nonamer.command.listener.DiscordCommandListener;
import me.conclure.nonamer.command.sender.ConsoleCommandSender;

public class CommandManager {
  private final ConsoleCommandSender consoleSender;
  private final CommandCoordinator coordinator;
  private final CommandInterceptor interceptor;
  private final CommandParser parser;
  private final ConsoleCommandListener consoleCommandListener;
  private final DiscordCommandListener discordCommandListener;

  public CommandManager(Bootstrap bootstrap, JDA jda) {
    this.coordinator = new CommandCoordinator(bootstrap);
    this.interceptor = new CommandInterceptor(this);
    this.consoleSender = new ConsoleCommandSender(this);
    this.consoleCommandListener = new ConsoleCommandListener(this);
    this.discordCommandListener = new DiscordCommandListener(jda, this);
    this.parser = new CommandParser();
  }

  public CommandInterceptor interceptor() {
    return this.interceptor;
  }

  public CommandParser parser() {
    return this.parser;
  }

  public CommandCoordinator coordinator() {
    return this.coordinator;
  }

  public void shutdown() {
    this.discordCommandListener.shutdown();
    this.consoleCommandListener.shutdown();
  }
}
