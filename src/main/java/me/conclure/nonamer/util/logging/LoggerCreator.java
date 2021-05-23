package me.conclure.nonamer.util.logging;

import org.slf4j.LoggerFactory;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public interface LoggerCreator {
  static Logger create(Class<?> clazz) {
    return new Logger(LoggerFactory.getLogger(clazz));
  }

  static Logger create(Object obj) {
    return new Logger(LoggerFactory.getLogger(obj.getClass()));
  }

  static Logger create(String name) {
    return new Logger(LoggerFactory.getLogger(name));
  }

}
