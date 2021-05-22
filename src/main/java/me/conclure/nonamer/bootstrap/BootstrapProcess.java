package me.conclure.nonamer.bootstrap;

public interface BootstrapProcess {
  boolean isRunning();

  boolean hasRunned();

  void await() throws InterruptedException;
}
