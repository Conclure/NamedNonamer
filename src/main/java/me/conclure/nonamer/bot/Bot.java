package me.conclure.nonamer.bot;

import me.conclure.nonamer.bootstrap.Bootstrap;
import net.dv8tion.jda.api.JDA;

import me.conclure.nonamer.command.CommandManager;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class Bot {
  private final Bootstrap bootstrap;
  private volatile JDA jda;
  private volatile CommandManager commandManager;

  public Bot(Bootstrap bootstrap) {
    this.bootstrap = bootstrap;
  }

  public Bootstrap bootstrap() {
    return bootstrap;
  }

  public JDA jda() {
    return this.jda;
  }

  public void setJda(JDA jda) {
    this.jda = jda;
  }

  public CommandManager commandManager() {
    return this.commandManager;
  }

  public void setCommandManager(CommandManager commandManager) {
    this.commandManager = commandManager;
  }
}