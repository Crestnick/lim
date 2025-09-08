package ru.javarush.lim.cryptoanalizer.io;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import ru.javarush.lim.cryptoanalizer.util.cipher.CaesarFileCipher;
import ru.javarush.lim.cryptoanalizer.util.cipher.Mode;
import ru.javarush.lim.cryptoanalizer.util.cipher.Process;
import ru.javarush.lim.cryptoanalizer.util.logger.Logger;
import ru.javarush.lim.cryptoanalizer.util.logger.LoggerSingleton;

public class CaesarFileRunner {

  private static final Logger logger = LoggerSingleton.getInstance();

  public static void run(String inputPath, String outputPath, CaesarFileCipher cipher, byte key, Mode mode) {
    cipher.receiveKey(key);
    logger.info("Запускаем CaesarFileRunner");

    try (BufferedReader reader = new BufferedReader(new FileReader(inputPath));
         BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {

      String line;
      while ((line = reader.readLine()) != null) {
        String result = cipher.process(line, mode);
        writer.write(result);
        writer.newLine();
      }

      logger.success("Файл успешно обработан: " + outputPath);

    } catch (IOException e) {
      logger.exception("CaesarFileRunner", "Ошибка при обработке файла", e);
    }
  }

}
