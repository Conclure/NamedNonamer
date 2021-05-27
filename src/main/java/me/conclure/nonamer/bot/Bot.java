package me.conclure.nonamer.bot;

import me.conclure.nonamer.bootstrap.Bootstrap;
import net.dv8tion.jda.api.JDA;

import me.conclure.nonamer.command.CommandManager;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class Bot {
  private final Bootstrap bootstrap;
  private final JDA jda;
  private final CommandManager commandManager;

  public Bot(Bootstrap bootstrap, JDA jda, CommandManager commandManager) {
    this.bootstrap = bootstrap;
    this.jda = jda;
    this.commandManager = commandManager;
  }

  public Bootstrap bootstrap() {
    return bootstrap;
  }

  public JDA jda() {
    return this.jda;
  }

  public CommandManager commandManager() {
    return this.commandManager;
  }
}