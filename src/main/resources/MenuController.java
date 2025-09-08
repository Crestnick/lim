package ru.javarush.lim.cryptoanalizer.ui;

import ru.javarush.lim.cryptoanalizer.io.CaesarFileRunner;
import ru.javarush.lim.cryptoanalizer.util.alphabet.AlphabetConfig;
import ru.javarush.lim.cryptoanalizer.util.cipher.CaesarFileCipher;
import ru.javarush.lim.cryptoanalizer.util.cipher.Mode;
import ru.javarush.lim.cryptoanalizer.util.logger.Logger;
import ru.javarush.lim.cryptoanalizer.util.logger.LoggerSingleton;

import java.util.Scanner;

public class MenuController {
  private final Logger logger = LoggerSingleton.getInstance();
  private final ConsoleView view = new ConsoleView();
  private final String symbols = AlphabetConfig.RUSSIAN;

  public void run() {
    while (true) {
      view.showMenu();
      String choice = view.readChoice();

      if (choice.equals("0")) {
        logger.info("Выход из программы. До встречи!");
        break;
      }

      handleChoice(choice);
    }
  }

  private void handleChoice(String choice) {
    CaesarFileCipher cipher = new CaesarFileCipher(symbols);
    Scanner scanner = view.getScanner();

    if (choice.equals("1")) {
      logger.info("Введите путь к входному файлу:");
      String inputPath = scanner.nextLine();

      logger.info("Введите путь к выходному файлу:");
      String outputPath = scanner.nextLine();

      Mode mode = readModeAndKey(scanner, cipher);
      CaesarFileRunner.run(inputPath, outputPath, cipher, cipher.getKey(), mode);

    } else if (choice.equals("2")) {
      logger.info("Введите текст:");
      String inputText = scanner.nextLine();

      Mode mode = readModeAndKey(scanner, cipher);
      String result = cipher.process(inputText, mode);
      logger.success("Результат: " + result);

    } else {
      logger.warn("Main", "❌ Неверный выбор. Попробуйте снова.");
    }
  }

  private Mode readModeAndKey(Scanner scanner, CaesarFileCipher cipher) {
    byte key = readKey(scanner);
    cipher.receiveKey(key);
    return readMode(scanner);
  }

  private byte readKey(Scanner scanner) {
    while (true) {
      logger.info("Введите ключ (целое число от -128 до 127):");
      String input = scanner.nextLine().trim();
      try {
        return Byte.parseByte(input);
      } catch (NumberFormatException e) {
        logger.fail("❌ Неверный формат. Попробуйте снова.");
      }
    }
  }

  private Mode readMode(Scanner scanner) {
    while (true) {
      logger.info("Режим (encrypt/decrypt):");
      String input = scanner.nextLine().trim().toLowerCase();
      switch (input) {
        case "encrypt" -> { return Mode.ENCRYPT; }
        case "decrypt" -> { return Mode.DECRYPT; }
        default -> logger.fail("❌ Неверный режим. Введите 'encrypt' или 'decrypt'.");
      }
    }
  }
}
