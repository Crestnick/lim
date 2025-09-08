package ru.javarush.lim.cryptoanalizer.util.cipher;


public interface ExtendedAcceptor extends KeyAcceptor {

  default byte receiveKey(byte key) {
    return KeyAcceptor.acceptKey(key, this);
  }
}
