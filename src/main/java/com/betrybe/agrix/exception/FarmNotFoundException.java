package com.betrybe.agrix.exception;

/**
 * Represents an exception for farm not found.
 */
public class FarmNotFoundException extends RuntimeException {
  public FarmNotFoundException() {
    super("Fazenda não encontrada!");
  }
}
