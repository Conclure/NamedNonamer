package me.conclure.nonamer.command.listener;

import me.conclure.nonamer.command.CommandInterceptor;
import me.conclure.nonamer.command.CommandManager;
import me.conclure.nonamer.command.sender.CommandSender;
import me.conclure.nonamer.command.sender.ConsoleCommandSender;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class ConsoleCommandListener extends Thread {
  private final CommandSender consoleSender;
  private final Scanner scanner = new Scanner(System.in);
  private final CommandManager commandManager;
  private final AtomicBoolean shutdown = new AtomicBoolean();

  public ConsoleCommandListener(CommandManager commandManager) {
    this.commandManager = commandManager;
    this.setName("console-command-publisher");
    this.start();
    this.consoleSender = new ConsoleCommandSender(commandManager);
  }

  @Override
  public void run() {
    CommandInterceptor interceptor = this.commandManager.interceptor();

    while (!this.shutdown.get() && this.scanner.hasNextLine()) {
      this.scanner.nextLine();
      interceptor.handle(this.consoleSender,this.scanner.nextLine());
    }

    this.scanner.close();
  }

  public void shutdown() {
    this.shutdown.set(false);
  }

}