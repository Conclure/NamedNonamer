package me.conclure.nonamer.util.concurrent;

import java.util.concurrent.TimeUnit;

public interface Awaiter {
  void await() throws InterruptedException;

  boolean await(long timeout, TimeUnit unit)
      throws InterruptedException;
}
