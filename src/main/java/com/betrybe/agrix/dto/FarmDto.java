package com.betrybe.agrix.dto;

import com.betrybe.agrix.entity.Farm;

/**
 * The type Farm dto.
 */
public record FarmDto(Long id, String name, Double size) {

  /**
   * Farm to FarmDto.
   */
  public static FarmDto fromEntity(Farm farm) {
    return new FarmDto(
        farm.getId(),
        farm.getName(),
        farm.getSize()
    );
  }

  /**
   *FarmDto to Farm.
   */
  public Farm toEntity() {
    Farm farm = new Farm();
    farm.setName(name);
    farm.setSize(size);

    return farm;
  }
}
