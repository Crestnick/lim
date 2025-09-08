package ru.javarush.lim.cryptoanalizer.util.alphabet;

import java.util.LinkedHashMap;
import java.util.Map;

public class RussianAlphabet implements Alphabet {

  @Override
  public Map<Character, Integer> build(String symbols) {
    Character[] array = symbols.chars()
        .mapToObj(c -> (char) c)
        .toArray(Character[]::new);

    return index(array);
  }
}
