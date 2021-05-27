package me.conclure.nonamer.bootstrap;

import com.neovisionaries.ws.client.WebSocketFactory;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.AnnotatedEventManager;
import net.dv8tion.jda.api.hooks.SubscribeEvent;

import me.conclure.nonamer.OptionContext;
import me.conclure.nonamer.bot.Bot;
import me.conclure.nonamer.command.CommandManager;
import me.conclure.nonamer.util.logging.Logger;
import me.conclure.nonamer.util.logging.LoggerCreator;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
class Loader extends AbstractBootstrapProcess {
  private final Bootstrap bootstrap;
  private final Logger logger = LoggerCreator.create(this);

  Loader(Bootstrap bootstrap) {
    this.bootstrap = bootstrap;
  }

  @Override
  protected void perform() throws Exception {
    OptionContext optionContext = this.bootstrap.optionContext();
    JDA jda = JDABuilder.createDefault(optionContext.token())
        .setStatus(OnlineStatus.DO_NOT_DISTURB)
        .setEventManager(new AnnotatedEventManager())
        .addEventListeners(new Object() {
          @SubscribeEvent
          public void onReady(ReadyEvent event) {
            event.getJDA().getPresence().setStatus(OnlineStatus.ONLINE);
          }
        })
        .build()
        .awaitReady();
    CommandManager commandManager = new CommandManager(this.bootstrap,jda);
    this.bootstrap.setBot(new Bot(bootstrap, jda, commandManager));

    this.logger.debug("Loaded!");
  }
}
