package ru.javarush.lim.cryptoanalizer.util.alphabet;

import java.util.Map;

@FunctionalInterface
public interface Alphabet extends Parser, Mapper {

  Map<Character, Integer> build(String symbols);
}