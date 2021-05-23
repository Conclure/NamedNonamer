package me.conclure.nonamer.command.listener;

import me.conclure.nonamer.command.intercept.CommandInterceptFlag;
import me.conclure.nonamer.command.intercept.CommandInterceptor;
import me.conclure.nonamer.command.CommandManager;

import java.util.EnumSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class ConsoleCommandListener extends Thread {
  private final Scanner scanner = new Scanner(System.in);
  private final CommandManager commandManager;
  private final AtomicBoolean shutdown = new AtomicBoolean();

  public ConsoleCommandListener(CommandManager commandManager) {
    this.commandManager = commandManager;
    this.setName("console-command-publisher");
    this.start();
  }

  @Override
  public void run() {
    CommandInterceptor interceptor = this.commandManager.interceptor();
    Set<CommandInterceptFlag> flagSet = EnumSet.of(CommandInterceptFlag.IGNORE_PREFIX);

    while (!this.shutdown.get() && this.scanner.hasNextLine()) {
      this.scanner.nextLine();
      interceptor.handle(this.scanner.nextLine(),flagSet);
    }
    this.scanner.close();
  }

  public void shutdown() {
    this.shutdown.set(false);
  }
}