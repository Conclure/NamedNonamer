package me.conclure.nonamer.command.sender;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

import java.util.concurrent.CompletableFuture;

public class DiscordCommandSender implements CommandSender {
  private final JDA jda;
  private final long userId;
  private final long guildId;
  private final long channelId;

  public DiscordCommandSender(JDA jda, long userId, long guildId, long channelId) {
    this.jda = jda;
    this.userId = userId;
    this.guildId = guildId;
    this.channelId = channelId;
  }

  @Override
  public CompletableFuture<PermissionResult> hasPermission(Permission permission) {
    Guild guild = this.jda.getGuildById(this.guildId);

    if (guild == null) {
      return CompletableFuture.completedFuture(PermissionResult.UNMATCHED_GUILD);
    }

    CompletableFuture<User> user = CompletableFuture.completedFuture(this.jda.getUserById(this.userId));

    if (user.join() == null) {
      user = this.jda.retrieveUserById(this.userId).submit();
    }

    TextChannel channel = this.jda.getTextChannelById(this.channelId);

    if (channel == null) {
      return CompletableFuture.completedFuture(PermissionResult.UNMATCHED_CHANNEL);
    }

    return user.thenApply(u -> {
      Member member = guild.getMember(u);

      if (member == null) {
        return PermissionResult.MEMBER_NOT_IN_GUILD;
      }

      if (member.hasPermission(channel,permission)) {
        return PermissionResult.TRUE;
      }

      return PermissionResult.FALSE;
    });

  }

  @Override
  public CompletableFuture<Void> sendMessage(String message) {
    return CompletableFuture.runAsync()
  }
}
