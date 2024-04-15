package com.betrybe.agrix.dto;

import com.betrybe.agrix.entity.Fertilizer;
import java.time.LocalDate;

/**
 * The type Fertilizer Dto.
 */
public record FertilizerDto(
    Long id,
    String name,
    String brand,
    String composition
) {
  /**
   * Fertilizer to FertilizerDto.
   */
  public static FertilizerDto fromEntity(Fertilizer fertilizer) {
    return new FertilizerDto(
        fertilizer.getId(),
        fertilizer.getName(),
        fertilizer.getBrand(),
        fertilizer.getComposition()
    );
  }

  /**
   * FertilizerDto to Fertilizer.
   */
  public Fertilizer toEntity() {
    Fertilizer fertilizer = new Fertilizer();
    fertilizer.setName(name);
    fertilizer.setBrand(brand);
    fertilizer.setComposition(composition);

    return fertilizer;
  }

}