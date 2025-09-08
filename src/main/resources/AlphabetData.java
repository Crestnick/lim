package ru.javarush.lim.cryptoanalizer.util.alphabet;

import java.util.LinkedHashMap;
import java.util.Map;

public record AlphabetData(Map<Character, Integer> direct, Map<Integer, Character> reverse, String symbols) {

  public static AlphabetData from(String symbols, Alphabet alphabet) {
    Map<Character, Integer> direct = alphabet.build(symbols);
    Map<Integer, Character> reverse = new LinkedHashMap<>();
    direct.forEach((ch, idx) -> reverse.put(idx, ch));
    return new AlphabetData(direct, reverse, symbols);
  }

  public String symbolsAsString() {
    return symbols;
  }
}
