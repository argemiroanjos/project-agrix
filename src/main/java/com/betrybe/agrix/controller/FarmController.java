package com.betrybe.agrix.controller;

import com.betrybe.agrix.dto.CropDto;
import com.betrybe.agrix.dto.FarmDto;
import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.entity.Farm;
import com.betrybe.agrix.exception.CropNotFoundException;
import com.betrybe.agrix.exception.FarmNotFoundException;
import com.betrybe.agrix.service.CropService;
import com.betrybe.agrix.service.FarmService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Farm controller.
 */
@RestController
@RequestMapping("/farms")
public class FarmController {

  private final FarmService farmService;
  private final CropService cropService;

  /**
   * Instantiates a new Farm controller.
   */
  @Autowired
  public FarmController(FarmService farmService, CropService cropService) {
    this.farmService = farmService;
    this.cropService = cropService;
  }

  /**
   * Create farm response entity.
   */
  @PostMapping
  public ResponseEntity<FarmDto> createFarm(@RequestBody FarmDto farmDto) {
    Farm createdFarm = farmService.createFarm(farmDto.toEntity());
    FarmDto farmDtoResponse = FarmDto.fromEntity(createdFarm);
    return ResponseEntity.status(HttpStatus.CREATED).body(farmDtoResponse);
  }

  /**
   * Get all farm response entity.
   */
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<FarmDto> getAllFarm() {
    List<Farm> findAllFarms = farmService.getAllFarm();
    return findAllFarms.stream().map(FarmDto::fromEntity).toList();
  }

  /**
   * Get farm by id response entity.
   */
  @GetMapping("/{id}")
  public FarmDto findById(@PathVariable Long id) throws FarmNotFoundException {
    Farm farm = farmService.getFarmById(id);
    return FarmDto.fromEntity(farm);
  }

  /**
   * Create crop response entity.
   */
  @PostMapping("/{farmId}/crops")
  public ResponseEntity<CropDto> createCrop(
      @RequestBody CropDto cropDto,
      @PathVariable Long farmId
  ) {
    Crop createdCrop = cropService.createCrop(cropDto.toEntity(), farmId);
    CropDto cropDtoResponse = CropDto.fromEntity(createdCrop);
    return ResponseEntity.status(HttpStatus.CREATED).body(cropDtoResponse);
  }

  /**
   * Get crop by farm id response entity.
   */
  @GetMapping("/{farmId}/crops")
  public List<CropDto> getCropById(@PathVariable Long farmId) throws CropNotFoundException {
    List<Crop> crops = cropService.getCropById(farmId);
    return crops.stream().map(crop -> CropDto.fromEntity(crop)).toList();
  }
}