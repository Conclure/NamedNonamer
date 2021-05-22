package me.conclure.nonamer.bootstrap;

import java.util.concurrent.CountDownLatch;

public abstract class AbstractBootstrapProcess implements Runnable, BootstrapProcess {
  private int runCode = 1;
  private final CountDownLatch latch = new CountDownLatch(1);

  protected abstract void perform();

  @Override
  public final void run() {
    this.runCode--;

    try {
      this.perform();
    } finally {
      this.runCode--;
      this.latch.countDown();
    }
  }

  @Override
  public final boolean isRunning() {
    return this.runCode == 0;
  }

  @Override
  public final boolean hasRunned() {
    return this.runCode == -1;
  }

  @Override
  public final void await() throws InterruptedException {
    this.latch.await();
  }
}
