package me.conclure.nonamer.bootstrap;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public abstract class AbstractBootstrapProcess implements Runnable, BootstrapProcess {
  private int runCode = 1;
  private final CountDownLatch latch = new CountDownLatch(1);

  protected abstract void perform() throws Exception;

  @Override
  public final void run() {
    if (this.runCode != 1) {
      return;
    }

    this.runCode--;

    try {
      this.perform();
    } catch (Exception exception) {
      throw new PerformException(exception);
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
  public final boolean hasRan() {
    return this.runCode == -1;
  }

  @Override
  public final void await() throws InterruptedException {
    this.latch.await();
  }

  @Override
  public final boolean await(long timeout, TimeUnit unit) throws InterruptedException {
    return this.latch.await(timeout, unit);
  }

  static class PerformException extends RuntimeException {
    public PerformException() {
      super();
    }

    public PerformException(String message) {
      super(message);
    }

    public PerformException(String message, Throwable cause) {
      super(message, cause);
    }

    public PerformException(Throwable cause) {
      super(cause);
    }
  }
}