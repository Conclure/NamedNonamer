package me.conclure.nonamer.bootstrap;

import me.conclure.nonamer.util.concurrent.Awaiter;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public interface BootstrapProcess extends Awaiter {
  boolean isRunning();

  boolean hasRan();

  default boolean hasNotRan() {
    return !this.hasRan() && !this.isRunning();
  }
}
