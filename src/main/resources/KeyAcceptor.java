package ru.javarush.lim.cryptoanalizer.util.cipher;

public interface KeyAcceptor extends KeyReceiver {

  static byte acceptKey(byte key, Object target) {
    if (target instanceof KeyReceiver receiver) {
      receiver.setKey(key);
      return key;
    } else {
      throw new IllegalArgumentException("Target does not implement KeyReceiver");
    }
  }
}
