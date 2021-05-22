package me.conclure.nonamer.bootstrap;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;

import me.conclure.nonamer.OptionContext;
import me.conclure.nonamer.bot.Bot;
import me.conclure.nonamer.util.logging.Logger;
import me.conclure.nonamer.util.logging.LoggerProvider;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
class Loader extends AbstractBootstrapProcess {
  private final Bootstrap bootstrap;
  private final Logger logger = LoggerProvider.get(this);

  Loader(Bootstrap bootstrap) {
    this.bootstrap = bootstrap;
  }

  @Override
  protected void perform() throws Exception {
    OptionContext optionContext = this.bootstrap.optionContext();
    JDA jda = JDABuilder.createDefault(optionContext.token())
        .setStatus(OnlineStatus.DO_NOT_DISTURB)
        .build()
        .awaitReady();
    this.bootstrap.setBot(new Bot(jda));
    this.logger.debug("Loaded!");
  }

}
