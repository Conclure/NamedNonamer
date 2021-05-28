package me.conclure.nonamer.command.listener;

import me.conclure.nonamer.bootstrap.Bootstrap;
import me.conclure.nonamer.command.CommandInterceptor;
import me.conclure.nonamer.command.CommandManager;
import me.conclure.nonamer.command.sender.CommandSender;
import me.conclure.nonamer.command.sender.ConsoleCommandSender;

import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class ConsoleCommandListener implements Runnable {
  private final CommandSender consoleSender;
  private final Scanner scanner = new Scanner(System.in);
  private final CommandManager commandManager;

  public ConsoleCommandListener(CommandManager commandManager) {
    this.commandManager = commandManager;
    this.consoleSender = new ConsoleCommandSender();
    Thread thread = new Thread(this);
    thread.setName("Console Listener");
    thread.start();
  }

  @Override
  public void run() {
    CommandInterceptor interceptor = this.commandManager.interceptor();

    while (this.scanner.hasNextLine()) {
      if (commandManager.shutdown()) {
        System.out.println(1);
        break;
      }

      interceptor.handle(this.consoleSender,this.scanner.nextLine());
    }

    this.scanner.close();
  }
}