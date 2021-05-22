package me.conclure.nonamer.bootstrap;

import me.conclure.nonamer.util.concurrent.Awaiter;

public interface BootstrapProcess extends Awaiter {
  boolean isRunning();

  boolean hasRunned();
}
