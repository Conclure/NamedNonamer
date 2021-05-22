package me.conclure.nonamer.util.logging;

import javax.annotation.ParametersAreNonnullByDefault;

import com.google.errorprone.annotations.FormatMethod;
import com.google.errorprone.annotations.FormatString;

@ParametersAreNonnullByDefault
public class Logger {
  private final org.slf4j.Logger delegatingLogger;

  public Logger(org.slf4j.Logger delegatingLogger) {
    this.delegatingLogger = delegatingLogger;
  }

  public void error(String msg) {
    this.delegatingLogger.error(msg);
  }

  @FormatMethod
  public void errorf(@FormatString String msg, Object... args) {
      this.error(String.format(msg,args));
  }

  public void error(Throwable cause) {
    this.delegatingLogger.error(cause.getMessage(),cause);
  }

  public void error(Throwable cause, String msg) {
    this.delegatingLogger.error(msg,cause);
  }

  @FormatMethod
  public void errorf(Throwable cause, @FormatString String msg, Object... args) {
    this.error(cause,String.format(msg,args));
  }

  public void warn(String msg) {
    this.delegatingLogger.warn(msg);
  }

  @FormatMethod
  public void warnf(@FormatString String msg, Object... args) {
    this.warn(String.format(msg,args));
  }

  public void warn(Throwable cause) {
    this.delegatingLogger.warn(cause.getMessage(),cause);
  }

  public void warn(Throwable cause, String msg) {
    this.delegatingLogger.warn(msg,cause);
  }

  @FormatMethod
  public void warnf(Throwable cause, @FormatString String msg, Object... args) {
    this.warn(cause,String.format(msg,args));
  }

  public void info(String msg) {
    this.delegatingLogger.info(msg);
  }

  @FormatMethod
  public void infof(@FormatString String msg, Object... args) {
    this.info(String.format(msg,args));
  }

  public void info(Throwable cause) {
    this.delegatingLogger.info(cause.getMessage(),cause);
  }

  public void info(Throwable cause, String msg) {
    this.delegatingLogger.info(msg,cause);
  }

  @FormatMethod
  public void infof(Throwable cause, @FormatString String msg, Object... args) {
    this.info(cause,String.format(msg,args));
  }

  public void debug(String msg) {
    this.delegatingLogger.debug(msg);
  }

  @FormatMethod
  public void debugf(@FormatString String msg, Object... args) {
    this.debug(String.format(msg,args));
  }

  public void debug(Throwable cause) {
    this.delegatingLogger.debug(cause.getMessage(),cause);
  }

  public void debug(Throwable cause, String msg) {
    this.delegatingLogger.debug(msg,cause);
  }

  @FormatMethod
  public void debugf(Throwable cause, @FormatString String msg, Object... args) {
    this.debug(cause,String.format(msg,args));
  }
}
