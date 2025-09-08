package ru.javarush.lim.cryptoanalizer.util.alphabet;

import java.util.Optional;

public interface Parser<T> {

  default Optional<T> parse(String input) {
    return Optional.empty();
  }

  default T parseOrThrow(String input) {
    return parse(input).orElseThrow(() ->
        new IllegalArgumentException("❌ Невозможно распарсить: " + input));
  }
}