package ru.javarush.lim.cryptoanalizer.util.logger;

public interface Logger {

  void info(String message);

  void success(String message);

  void fail(String message);

  void debug(String source, String message);

  void warn(String source, String message);

  void error(String source, String message);

  void exception(String source, String message, Throwable throwable);

}





