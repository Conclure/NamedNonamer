package me.conclure.nonamer;

import joptsimple.OptionParser;

import me.conclure.nonamer.bootstrap.Bootstrap;


import javax.annotation.ParametersAreNonnullByDefault;

import com.google.errorprone.annotations.Var;

@ParametersAreNonnullByDefault
public final class Main {

  public static void main(String[] args) {
    OptionParser parser = new ArgumentParser();
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
      throw new RuntimeException(e);
    }
  }

  static class ArgumentParser extends OptionParser {
    {
      this.accepts("token", "Bot token")
          .withRequiredArg()
          .required();
    }
  }
}