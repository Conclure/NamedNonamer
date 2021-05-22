package me.conclure.nonamer.bootstrap;

import me.conclure.nonamer.OptionContext;
import me.conclure.nonamer.bootstrap.AbstractBootstrapProcess.PerformException;
import me.conclure.nonamer.bot.Bot;
import me.conclure.nonamer.util.logging.Logger;
import me.conclure.nonamer.util.logging.LoggerProvider;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import javax.annotation.ParametersAreNonnullByDefault;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

@ParametersAreNonnullByDefault
public class Bootstrap {
  private final CountDownLatch terminationLatch = new CountDownLatch(1);
  private final Logger logger = LoggerProvider.get(this);
  private final OptionContext optionContext;
  private final ExecutorService bootstrapThread;
  private final AbstractBootstrapProcess enableProcess;
  private final AbstractBootstrapProcess disableProcess;
  private Bot bot;

  public Bootstrap(OptionContext context) {
    this.optionContext = context;
    ThreadFactory threadFactory = new ThreadFactoryBuilder()
        .setDaemon(true)
        .setNameFormat("bootstrap-executor")
        .setThreadFactory(Executors.defaultThreadFactory())
        .setUncaughtExceptionHandler(new UncaughtExceptionHandler())
        .build();
    this.bootstrapThread = Executors.newSingleThreadExecutor(threadFactory);
    this.enableProcess = new Loader(this);
    this.disableProcess = new Terminator(this);
  }

  public void enable() {
    this.bootstrapThread.execute(() -> {
      try {
        this.enableProcess.run();
      } catch (PerformException exception) {
        this.logger.error(exception);
        this.terminationLatch.countDown();
      }
    });
  }

  public void disable() {
    this.bootstrapThread.execute(() -> {
      try {
        this.disableProcess.run();
      } catch (PerformException exception) {
        this.logger.error(exception);
      } finally {
        this.terminationLatch.countDown();
      }
    });
  }

  public void awaitTermination() throws InterruptedException {
    this.terminationLatch.await();
  }

  public OptionContext optionContext() {
    return this.optionContext;
  }

  public BootstrapProcess enableProcess() {
    return this.enableProcess;
  }

  public BootstrapProcess disableProcess() {
    return this.disableProcess;
  }

  public Bot bot() {
    return this.bot;
  }

  public void setBot(Bot bot) {
    this.bot = bot;
  }

  static class UncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
      e.printStackTrace();
    }
  }
}