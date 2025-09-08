package ru.javarush.lim.cryptoanalizer.util.cipher;

public interface KeyReceiver {

  default void setKey(byte key) {
    System.out.println("[KeyReceiver] Key set to: " + key);
  }
}
