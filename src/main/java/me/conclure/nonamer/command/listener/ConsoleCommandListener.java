package me.conclure.nonamer.command.listener;

import net.dv8tion.jda.api.Permission;

import me.conclure.nonamer.command.CommandInterceptor;
import me.conclure.nonamer.command.CommandManager;
import me.conclure.nonamer.command.CommandSender;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class ConsoleCommandListener extends Thread {
  public static final CommandSender CONSOLE_SENDER = new ConsoleSender();

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

    while (!this.shutdown.get() && this.scanner.hasNextLine()) {
      this.scanner.nextLine();
      interceptor.handle(CONSOLE_SENDER,this.scanner.nextLine());
    }
    this.scanner.close();
  }

  public void shutdown() {
    this.shutdown.set(false);
  }

  static class ConsoleSender implements CommandSender {

    @Override
    public boolean hasPermission(String permission) {
      return true;
    }

    @Override
    public boolean hasPermission(Permission permission) {
      return true;
    }
  }
}