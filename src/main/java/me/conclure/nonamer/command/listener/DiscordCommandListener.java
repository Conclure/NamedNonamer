package me.conclure.nonamer.command.listener;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.SubscribeEvent;

import me.conclure.nonamer.command.CommandManager;
import me.conclure.nonamer.command.CommandSender;
import me.conclure.nonamer.command.parse.CommandParseFlag;

import java.util.EnumSet;
import java.util.Set;


public class DiscordCommandListener {
  private final Set<CommandParseFlag> flagSet;
  private final CommandManager commandManager;
  private final JDA jda;

  public DiscordCommandListener(JDA jda, CommandManager commandManager) {
    this.jda = jda;
    this.commandManager = commandManager;
    this.flagSet = EnumSet.of(CommandParseFlag.ASSERT_PREFIX);
    jda.addEventListener(this);
  }

  @SubscribeEvent
  public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
    Message message = event.getMessage();

    if (message.isWebhookMessage()) {
      return;
    }

    User author = message.getAuthor();

    if (author.isBot()) {
      return;
    }

    CommandSender sender = new DiscordCommandSender(this.jda, author.getIdLong(), message.getGuild().getIdLong());
    this.commandManager.interceptor().handle(sender, message.getContentStripped(), this.flagSet);
  }

  public void shutdown() {
    this.jda.removeEventListener(this);
  }

}
