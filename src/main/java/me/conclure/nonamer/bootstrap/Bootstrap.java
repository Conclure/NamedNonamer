package me.conclure.nonamer.bootstrap;

import me.conclure.nonamer.OptionContext;
import me.conclure.nonamer.bootstrap.AbstractBootstrapProcess.PerformException;
import me.conclure.nonamer.bot.Bot;
import me.conclure.nonamer.util.logging.Logger;
import me.conclure.nonamer.util.logging.LoggerCreator;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.ParametersAreNonnullByDefault;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

@ParametersAreNonnullByDefault
public class Bootstrap {
  private final CountDownLatch terminationLatch = new CountDownLatch(1);
  private final Logger logger = LoggerCreator.create(this);
  private final ExecutorService bootstrapThread = Executors.newSingleThreadExecutor(new ThreadFactoryBuilder()
      .setDaemon(true)
      .setNameFormat("bootstrap-executor")
      .setThreadFactory(Executors.defaultThreadFactory())
      .setUncaughtExceptionHandler(new UncaughtExceptionHandler())
      .build());
  private final AbstractBootstrapProcess enableProcess = new Loader(this);;
  private final AbstractBootstrapProcess disableProcess = new Terminator(this);;
  private final OptionContext optionContext;
  private Bot bot;

  public Bootstrap(OptionContext context) {
    this.optionContext = context;
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

  public Logger logger() {
    return this.logger;
  }

  static class UncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
      e.printStackTrace();
    }
  }
}