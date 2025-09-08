package ru.javarush.lim.cryptoanalizer.util.cipher;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

public class CaesarFileCipher implements Cipher<String>, Process<String>, ExtendedAcceptor {

  private final Map<Character, Integer> directMap = new LinkedHashMap<>();
  private final Map<Integer, Character> reverseMap = new LinkedHashMap<>();
  private int size;


  public CaesarFileCipher(String alphabet) {
    char[] chars = alphabet.toCharArray();
    for (int i = 0; i < chars.length; i++) {
      directMap.put(chars[i], i);
      reverseMap.put(i, chars[i]);
    }
    size = chars.length;
  }

  private byte key;

  @Override
  public void setKey(byte key) {
    this.key = key;
  }


  public byte getKey() {
    return key;
  }
  @Override
  public String apply(String input) {
    return encrypt(input);
  }

  private String encrypt(String input) {
    return shift(input, key);
  }

  private String decrypt(String input) {
    return shift(input, (byte) -key);
  }

  private String shift(String input, byte key) {
    StringBuilder result = new StringBuilder();
    for (char c : input.toCharArray()) {
      Integer index = directMap.get(c);
      if (index != null) {
        int shifted = (index + key + size) % size;
        result.append(reverseMap.get(shifted));
      } else {
        result.append(c);
      }
    }
    return result.toString();
  }

  @Override
  public Map<Mode, Function<String, String>> getActions() {
    return Map.of(
        Mode.ENCRYPT, this::encrypt,
        Mode.DECRYPT, this::decrypt
    );
  }
}
