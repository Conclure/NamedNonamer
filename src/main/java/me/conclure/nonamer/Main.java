package me.conclure.nonamer;

import joptsimple.OptionParser;

import me.conclure.nonamer.bootstrap.Bootstrap;

import java.util.concurrent.CompletableFuture;

import com.google.errorprone.annotations.Var;

public final class Main {

  public static void main(String[] args) {
    OptionParser parser = new OptionParser();
    @Var OptionContext context;

    try {
      context = OptionContext.from(parser.parse(args));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    Bootstrap bootstrap = new Bootstrap(context);
    bootstrap.enable();

    try {
      bootstrap.awaitTermination();
    } catch (InterruptedException e) {
      bootstrap.disable();
      e.printStackTrace();
    }
  }

  static class ArgumentParser extends OptionParser {
    {
      this.accepts("token", "Bot token").withRequiredArg().required();
    }
  }
}