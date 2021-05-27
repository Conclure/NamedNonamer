package me.conclure.nonamer.command.sender;

import net.dv8tion.jda.api.Permission;

import me.conclure.nonamer.command.CommandManager;
import me.conclure.nonamer.command.sender.CommandSender;
import me.conclure.nonamer.command.sender.PermissionResult;
import me.conclure.nonamer.util.logging.Logger;
import me.conclure.nonamer.util.logging.LoggerCreator;

import java.util.concurrent.CompletableFuture;

public class ConsoleCommandSender implements CommandSender {

  @Override
  public CompletableFuture<PermissionResult> hasPermission(Permission permission) {
    return CompletableFuture.completedFuture(PermissionResult.TRUE);
  }

  @Override
  public CompletableFuture<Void> sendMessage(String message) {
    System.out.println(message);
    return new CompletableFuture<>();
  }

  @Override
  public CompletableFuture<String> name() {
    return CompletableFuture.completedFuture("CONSOLE");
  }
}
