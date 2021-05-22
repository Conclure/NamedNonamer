package me.conclure.nonamer.bootstrap;

import org.slf4j.Logger;

import me.conclure.nonamer.util.logging.LoggerProvider;

class Terminator extends AbstractBootstrapProcess {
  private final Bootstrap bootstrap;
  private final Logger logger = LoggerProvider.get(this);

  Terminator(Bootstrap bootstrap) {
    this.bootstrap = bootstrap;
  }

  @Override
  protected void perform() {
    this.logger.debug("disabled");
  }
}
