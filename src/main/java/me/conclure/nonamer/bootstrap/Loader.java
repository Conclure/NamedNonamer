package me.conclure.nonamer.bootstrap;

import org.slf4j.Logger;

import me.conclure.nonamer.util.logging.LoggerProvider;

class Loader extends AbstractBootstrapProcess {
  private final Bootstrap bootstrap;
  private final Logger logger = LoggerProvider.get(this);

  Loader(Bootstrap bootstrap) {
    this.bootstrap = bootstrap;
  }

  @Override
  protected void perform() {
    this.logger.debug("loaded");
  }

}
