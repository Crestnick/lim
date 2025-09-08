package ru.javarush.lim.cryptoanalizer.util.logger;

public final class LoggerStyle {

  private LoggerStyle() {
  }

  public enum TextColor {
    RESET("\u001B[0m"),
    BLACK("\u001B[30m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    BLUE("\u001B[34m"),
    PURPLE("\u001B[35m"),
    CYAN("\u001B[36m"),
    WHITE("\u001B[37m");

    private final String code;

    TextColor(String code) {
      this.code = code;
    }

    public String getCode() {
      return code;
    }
  }


  public enum BackgroundColor {
    RESET("\u001B[0m"),
    BLACK("\u001B[40m"),
    RED("\u001B[41m"),
    GREEN("\u001B[42m"),
    YELLOW("\u001B[43m"),
    BLUE("\u001B[44m"),
    PURPLE("\u001B[45m"),
    CYAN("\u001B[46m"),
    WHITE("\u001B[47m");

    private final String code;

    BackgroundColor(String code) {
      this.code = code;
    }

    public String getCode() {
      return code;
    }
  }

  public enum LogLevel {
    DEBUG,
    WARN,
    ERROR,
    EXCEPTION
  }

  public static String formatMessage(LogLevel level, String source, String message,
      Throwable throwable) {
    String color = switch (level) {
      case DEBUG -> TextColor.CYAN.getCode();
      case WARN -> TextColor.YELLOW.getCode();
      case ERROR, EXCEPTION -> TextColor.RED.getCode();
    };

    String prefix = switch (level) {
      case DEBUG -> "[DEBUG]";
      case WARN -> "[WARN]";
      case ERROR -> "[ERROR]";
      case EXCEPTION -> "[EXCEPTION]";
    };

    String fullMessage = prefix + " " + source + ": " + message;

    if (throwable != null) {
      fullMessage += " — " + throwable.getMessage();
    }

    return color + fullMessage + TextColor.RESET.getCode();
  }


  public static String successConsole(String message) {
    return BackgroundColor.GREEN.getCode() + TextColor.BLACK.getCode() + " " + message + " "
        + TextColor.RESET.getCode();
  }

  public static String failConsole(String message) {
    return BackgroundColor.RED.getCode() + TextColor.BLACK.getCode() + " " + message + " "
        + TextColor.RESET.getCode();
  }

  public static String info(String message) {
    return TextColor.CYAN.getCode() + message + TextColor.RESET.getCode();
  }
}