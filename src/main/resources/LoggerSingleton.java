package ru.javarush.lim.cryptoanalizer.util.logger;

import ru.javarush.lim.cryptoanalizer.util.logger.LoggerStyle.LogLevel;

public final class LoggerSingleton implements Logger {

  private static final LoggerSingleton INSTANCE = new LoggerSingleton();

  private LoggerSingleton() {

  }

  public static Logger getInstance() {
    return INSTANCE;
  }

  @Override
  public void debug(String source, String message) {
    System.out.println(LoggerStyle.formatMessage(LogLevel.DEBUG, source, message, null));
    LogManager.log(source, LogLevel.DEBUG, message, null);
  }

  @Override
  public void info(String message) {
    System.out.println(LoggerStyle.info(message));
  }

  @Override
  public void warn(String source, String message) {
    System.out.println(LoggerStyle.formatMessage(LogLevel.WARN, source, message, null));
    LogManager.log(source, LogLevel.WARN, message, null);
  }

  @Override
  public void error(String source, String message) {
    System.out.println(LoggerStyle.formatMessage(LogLevel.ERROR, source, message, null));
    LogManager.log(source, LogLevel.ERROR, message, null);
  }

  @Override
  public void exception(String source, String message, Throwable throwable) {
    System.out.println(LoggerStyle.formatMessage(LogLevel.EXCEPTION, source, message, throwable));
    LogManager.log(source, LogLevel.EXCEPTION, message, throwable);
  }

  @Override
  public void success(String message) {
    System.out.println(LoggerStyle.successConsole(message));
  }

  @Override
  public void fail(String message) {
    System.out.println(LoggerStyle.failConsole(message));
  }
}
