package com.betrybe.agrix.exception;

/**
 * Represents an exception for Fertilizer not found.
 */
public class FertilizerNotFoundException extends RuntimeException {
  public FertilizerNotFoundException() {
    super("Fertilizante não encontrado!");
  }

}
