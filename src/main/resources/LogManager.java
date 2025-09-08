package ru.javarush.lim.cryptoanalizer.util.logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import ru.javarush.lim.cryptoanalizer.util.logger.LoggerStyle.LogLevel;

public final class LogManager {

  private static final DateTimeFormatter FORMATTER =
      DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  private static final String LOG_DIR = "logs";

  private static Logger logger = LoggerSingleton.getInstance();

  private LogManager() {
    throw new UnsupportedOperationException("This utility class cannot be instantiated");
  }

  public static void setLogger(Logger customLogger) {
   logger = customLogger;
   }

  public static void log(String source, LogLevel level, String message, Throwable throwable) {
    String timestamp = LocalDateTime.now().format(FORMATTER);
    StringBuilder logLine = new StringBuilder(
        "[%s] [%s] [%s] %s".formatted(timestamp, level, source, message));

    if (throwable != null) {
      logLine.append(" — ")
          .append(throwable.getClass().getSimpleName())
          .append(": ")
          .append(throwable.getMessage());
    }

    writeToFile(source, logLine.toString());
  }

  private static void writeToFile(String source, String logLine) {
    String fileName = "%s.log".formatted(source);
    Path logPath = Path.of(LOG_DIR, fileName);

    try {
      java.nio.file.Files.createDirectories(logPath.getParent());
      try (BufferedWriter writer = new BufferedWriter(new FileWriter(logPath.toFile(), true))) {
        writer.write(logLine);
        writer.newLine();
      }
    } catch (IOException e) {
      if (logger != null) {
        logger.warn("LogManager", "Ошибка записи лога:");
      } else {
        System.out.println("Ошибка записи лога: ");
      }
    }
  }
}
