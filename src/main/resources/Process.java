package ru.javarush.lim.cryptoanalizer.util.cipher;

import java.util.Map;
import java.util.function.Function;

public interface Process<T> {



  default Map<Mode, Function<T, T>> getActions() {
    return Map.of(
        Mode.ENCRYPT, Function.identity(),
        Mode.DECRYPT, Function.identity()
    );
  }

  default T process(T input, Mode mode) {
    Function<T, T> action = getActions().get(mode);
    if (action == null) {
      throw new IllegalArgumentException("Unsupported mode: " + mode);
    }
    return action.apply(input);
  }
}
