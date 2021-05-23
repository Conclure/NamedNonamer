package me.conclure.nonamer.command.listener;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.SubscribeEvent;

import me.conclure.nonamer.command.CommandManager;


public class DiscordCommandListener {
  private final CommandManager commandManager;
  private final JDA jda;

  public DiscordCommandListener(JDA jda, CommandManager commandManager) {
    this.jda = jda;
    this.commandManager = commandManager;
    jda.addEventListener(this);
  }

  @SubscribeEvent
  public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
    Message message = event.getMessage();

    if (message.isWebhookMessage()) {
      return;
    }

    if (message.getAuthor().isBot()) {
      return;
    }

    this.commandManager.interceptor().handle(message.getContentStripped());
  }

  public void shutdown() {
    this.jda.removeEventListener(this);
  }
}
