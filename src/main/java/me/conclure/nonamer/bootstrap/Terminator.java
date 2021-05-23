package me.conclure.nonamer.bootstrap;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDA.Status;

import me.conclure.nonamer.bot.Bot;
import me.conclure.nonamer.util.logging.Logger;
import me.conclure.nonamer.util.logging.LoggerCreator;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
class Terminator extends AbstractBootstrapProcess {
  private final Bootstrap bootstrap;
  private final Logger logger = LoggerCreator.get(this);

  Terminator(Bootstrap bootstrap) {
    this.bootstrap = bootstrap;
  }

  @Override
  protected void perform() throws Exception {
    Bot bot = this.bootstrap.bot();
    bot.commandManager().shutdown();
    JDA jda = bot.jda();
    jda.shutdown();
    jda.awaitStatus(Status.SHUTDOWN);

    this.logger.debug("Terminated!");
  }
}
