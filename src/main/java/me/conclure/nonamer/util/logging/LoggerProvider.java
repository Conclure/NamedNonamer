package me.conclure.nonamer.util.logging;

import org.slf4j.LoggerFactory;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public interface LoggerProvider {
  static Logger get(Class<?> clazz) {
    return new Logger(LoggerFactory.getLogger(clazz));
  }

  static Logger get(Object obj) {
    return new Logger(LoggerFactory.getLogger(obj.getClass()));
  }

  static Logger get(String name) {
    return new Logger(LoggerFactory.getLogger(name));
  }

}
