package me.conclure.nonamer.command;

import me.conclure.nonamer.bot.Bot;
import net.dv8tion.jda.api.JDA;

import me.conclure.nonamer.bootstrap.Bootstrap;
import me.conclure.nonamer.command.parse.CommandParser;
import me.conclure.nonamer.command.listener.ConsoleCommandListener;
import me.conclure.nonamer.command.listener.DiscordCommandListener;
import me.conclure.nonamer.command.sender.ConsoleCommandSender;

import java.util.concurrent.atomic.AtomicBoolean;

public class CommandManager {
  private final CommandCoordinator coordinator;
  private final CommandInterceptor interceptor;
  private final CommandParser parser;
  private final ConsoleCommandListener consoleCommandListener;
  private final DiscordCommandListener discordCommandListener;
  private final AtomicBoolean shutdown = new AtomicBoolean();


  public CommandManager(Bot bot) {
    this.parser = new CommandParser();
    this.interceptor = new CommandInterceptor(bot);
    this.coordinator = new CommandCoordinator(bot);
    this.consoleCommandListener = new ConsoleCommandListener(this);
    this.discordCommandListener = new DiscordCommandListener(bot);
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

  public boolean shutdown() {
    return this.shutdown.get();
  }

  public void signalShutdown() {
    this.shutdown.set(true);
  }
}
