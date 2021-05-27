package me.conclure.nonamer.command.sender;

import net.dv8tion.jda.api.Permission;

import java.util.concurrent.CompletableFuture;

public interface CommandSender {
  CompletableFuture<PermissionResult> hasPermission(Permission permission);

  CompletableFuture<Void> sendMessage(String message);

  CompletableFuture<String> name();
}
