package ru.javarush.lim.cryptoanalizer.util.alphabet;

import java.util.LinkedHashMap;
import java.util.Map;

public interface Mapper<T, K> {

  @SuppressWarnings("unchecked")
  default K convert(int index) {
    return (K) Integer.valueOf(index);
  }

  default Map<T, K> index(T[] array) {
    Map<T, K> map = new LinkedHashMap<>();
    for (int i = 0; i < array.length; i++) {
      final int index = i;
      T item = array[i];
      map.computeIfAbsent(item, k -> convert(index));
    }
    return map;
  }
}