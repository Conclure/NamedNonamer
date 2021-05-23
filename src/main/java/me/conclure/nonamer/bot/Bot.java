package me.conclure.nonamer.bot;

import net.dv8tion.jda.api.JDA;

import me.conclure.nonamer.command.CommandManager;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class Bot {
  private final JDA jda;
  private final CommandManager commandManager;

  public Bot(JDA jda, CommandManager commandManager) {
    this.jda = jda;
    this.commandManager = commandManager;
  }

  public JDA jda() {
    return this.jda;
  }

  public CommandManager commandManager() {
    return this.commandManager;
  }
}