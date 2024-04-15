package com.betrybe.agrix.advice;

import com.betrybe.agrix.exception.CropNotFoundException;
import com.betrybe.agrix.exception.FarmNotFoundException;
import com.betrybe.agrix.exception.FertilizerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Representa um ControllerAdvice para as exceções.
 */
@ControllerAdvice
public class GeneralControllerAdvice {
  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleException(
      Exception exp) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno!");
  }

  @ExceptionHandler(FarmNotFoundException.class)
  public ResponseEntity<String> handleFarmNotFoundException(
      FarmNotFoundException exp) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fazenda não encontrada!");
  }

  @ExceptionHandler(CropNotFoundException.class)
  public ResponseEntity<String> handleCropNotFoundException(
      CropNotFoundException exp) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plantação não encontrada!");
  }

  @ExceptionHandler(FertilizerNotFoundException.class)
  public ResponseEntity<String> handleFertilizerNotFoundException(
      FertilizerNotFoundException exp) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fertilizante não encontrado!");
  }
}
