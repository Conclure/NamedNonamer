package me.conclure.nonamer.command;

import net.dv8tion.jda.api.JDA;

import me.conclure.nonamer.command.publisher.ConsoleCommandListener;
import me.conclure.nonamer.command.publisher.DiscordCommandListener;

public class CommandManager {
  private final CommandInterceptor interceptor;
  private final ConsoleCommandListener consoleCommandListener;
  private final DiscordCommandListener discordCommandListener;

  public CommandManager(JDA jda) {
    this.interceptor = new CommandInterceptor();
    this.consoleCommandListener = new ConsoleCommandListener(this.interceptor);
    this.discordCommandListener = new DiscordCommandListener(jda, this.interceptor);
  }

  public void shutdown() {
    this.discordCommandListener.shutdown();
    this.consoleCommandListener.shutdown();
  }
}
