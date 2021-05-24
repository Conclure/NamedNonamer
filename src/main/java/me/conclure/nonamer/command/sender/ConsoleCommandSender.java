package me.conclure.nonamer.command.sender;

import net.dv8tion.jda.api.Permission;

import me.conclure.nonamer.command.CommandManager;
import me.conclure.nonamer.command.sender.CommandSender;
import me.conclure.nonamer.command.sender.PermissionResult;
import me.conclure.nonamer.util.logging.Logger;
import me.conclure.nonamer.util.logging.LoggerCreator;

import java.util.concurrent.CompletableFuture;

public class ConsoleCommandSender implements CommandSender {
  private final CommandManager commandManager;
  private final Logger logger = LoggerCreator.create("console");

  public ConsoleCommandSender(CommandManager commandManager) {
    this.commandManager = commandManager;
  }

  @Override
  public PermissionResult hasPermission(Permission permission) {
    return PermissionResult.TRUE;
  }

  public CompletableFuture<Void> sendMessage(String message) {
    return CompletableFuture.runAsync(() -> this.logger.info(message), this.commandManager.coordinator().executor());
  }
}
