package me.conclure.nonamer.command.publisher;

import me.conclure.nonamer.command.CommandInterceptor;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class ConsoleCommandListener extends Thread {
  private final Scanner scanner = new Scanner(System.in);
  private final CommandInterceptor interceptor;
  private final AtomicBoolean shutdown = new AtomicBoolean();

  public ConsoleCommandListener(CommandInterceptor interceptor) {
    this.interceptor = interceptor;
    this.setName("console-command-publisher");
    this.start();
  }

  @Override
  public void run() {
    while (!this.shutdown.get() && this.scanner.hasNextLine()) {
      this.scanner.nextLine();
      this.interceptor.handle(this.scanner.nextLine());
    }
    this.scanner.close();
  }

  public void shutdown() {
    this.shutdown.set(false);
  }
}