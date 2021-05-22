package me.conclure.nonamer.util.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface LoggerProvider {
  static Logger get(Class<?> clazz) {
    return LoggerFactory.getLogger(clazz);
  }

  static Logger get(Object obj) {
    return LoggerFactory.getLogger(obj.getClass());
  }

}
