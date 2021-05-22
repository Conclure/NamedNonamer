package me.conclure.nonamer.bot;

import net.dv8tion.jda.api.JDA;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class Bot {
  private final JDA jda;

  public Bot(JDA jda) {
    this.jda = jda;
  }

  public JDA jda() {
    return this.jda;
  }
}