package com.betrybe.agrix.exception;

/**
 * Represents an exception for crop not found.
 */
public class CropNotFoundException extends RuntimeException {
  public CropNotFoundException() {
    super("Plantação não encontrada!");
  }
}
