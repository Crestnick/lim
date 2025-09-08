package ru.javarush.lim.cryptoanalizer.ui;

import java.util.Scanner;
import ru.javarush.lim.cryptoanalizer.util.logger.Logger;
import ru.javarush.lim.cryptoanalizer.util.logger.LoggerSingleton;

public class ConsoleView {
  private final Scanner scanner = new Scanner(System.in);
  private final Logger logger = LoggerSingleton.getInstance();

  public void showMenu() {
    logger.info("\n=== МЕНЮ ===");
    logger.info("1 — Шифровать/дешифровать файл");
    logger.info("2 — Шифровать/дешифровать текст с консоли");
    logger.info("0 — Выход");
    logger.info("Выберите действие: ");
  }

  public String readChoice() {
    return scanner.nextLine().trim();
  }

  public Scanner getScanner() {
    return scanner;
  }
}
