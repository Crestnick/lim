  package ru.javarush.lim.cryptoanalizer.util.cipher;


  @FunctionalInterface
  public interface Cipher<T> {

    String apply(T input);
  }
