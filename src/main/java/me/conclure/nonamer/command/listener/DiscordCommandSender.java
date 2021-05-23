package me.conclure.nonamer.command.listener;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.GuildChannel;

import me.conclure.nonamer.command.CommandSender;

public class DiscordCommandSender implements CommandSender {
  private final JDA jda;
  private final long userId;
  private final long guildId;

  DiscordCommandSender(JDA jda, long userId, long guildId) {
    this.jda = jda;
    this.userId = userId;
    this.guildId = guildId;
  }

  @Override
  public boolean hasPermission(String permission) {
    return false;
  }

  @Override
  public boolean hasPermission(Permission permission) {
    return false;
  }

  public boolean hasPermission(GuildChannel channel, Permission permission) {
    return false;
  }
}
