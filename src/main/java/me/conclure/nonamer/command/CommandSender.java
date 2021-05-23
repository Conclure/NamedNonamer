package me.conclure.nonamer.command;

import net.dv8tion.jda.api.Permission;

public interface CommandSender {
  boolean hasPermission(String permission);

  boolean hasPermission(Permission permission);
}
