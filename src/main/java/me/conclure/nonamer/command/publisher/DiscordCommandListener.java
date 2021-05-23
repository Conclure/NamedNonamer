package me.conclure.nonamer.command.publisher;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.SubscribeEvent;

import me.conclure.nonamer.command.CommandInterceptor;


public class DiscordCommandListener {
  private final CommandInterceptor interceptor;
  private final JDA jda;

  public DiscordCommandListener(JDA jda, CommandInterceptor interceptor) {
    this.jda = jda;
    this.interceptor = interceptor;
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

    this.interceptor.handle(message.getContentStripped());
  }

  public void shutdown() {
    this.jda.removeEventListener(this);
  }
}
