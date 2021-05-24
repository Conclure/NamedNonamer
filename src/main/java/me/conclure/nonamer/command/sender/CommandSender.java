package me.conclure.nonamer.command.sender;

import net.dv8tion.jda.api.Permission;

public interface CommandSender {
  PermissionResult hasPermission(Permission permission);
}
