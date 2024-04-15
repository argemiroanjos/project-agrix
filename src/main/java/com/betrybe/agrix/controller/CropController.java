package com.betrybe.agrix.controller;

import com.betrybe.agrix.dto.CropDto;
import com.betrybe.agrix.dto.FertilizerDto;
import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.entity.Fertilizer;
import com.betrybe.agrix.exception.CropNotFoundException;
import com.betrybe.agrix.exception.FertilizerNotFoundException;
import com.betrybe.agrix.service.CropService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Crop controller.
 */
@RestController
@RequestMapping("/crops")
public class CropController {

  private final CropService cropService;

  /**
   * Instantiates a new Crop controller.
   */
  public CropController(CropService cropService) {
    this.cropService = cropService;
  }

  /**
   * Get all Crop response entity.
   */
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<CropDto> getAllCrop() {
    List<Crop> findAllCrops = cropService.getAllCrop();
    List<CropDto> cropDtos = findAllCrops.stream().map(CropDto::fromEntity).toList();
    return cropDtos;
  }

  /**
   * Get  Crop by Id response entity.
   */
  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public CropDto getCropById(@PathVariable("id") Long id) throws CropNotFoundException {
    Crop crop = cropService.findById(id);
    return CropDto.fromEntity(crop);
  }

  /**
   * Get  Crop by harvest Date.
   */
  @GetMapping("/search")
  @ResponseStatus(HttpStatus.OK)
  public List<CropDto> findByHarvestDateRange(
      @RequestParam LocalDate start,
      @RequestParam LocalDate end
  ) {
    List<Crop> crops = cropService.findByHarvestDateRange(start, end);
    return crops.stream().map(CropDto::fromEntity).toList();
  }

  /**
   * Post  Crop with Fertilizer by Id.
   */
  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  @ResponseStatus(HttpStatus.CREATED)
  public String addFertilizerToCrop(
      @PathVariable("cropId") Long cropId,
      @PathVariable("fertilizerId") Long fertilizerId
  ) throws CropNotFoundException, FertilizerNotFoundException {
    Crop crop = cropService.updateCropFertilizer(cropId, fertilizerId);
    return "Fertilizante e plantação associados com sucesso!";
  }

  /**
   * Get Crops Fertilizers.
   */
  @GetMapping("/{cropId}/fertilizers")
  @ResponseStatus(HttpStatus.OK)
  public List<FertilizerDto> findFertilizerByCropId(@PathVariable("cropId") Long cropId)
      throws CropNotFoundException {
    Crop crop = cropService.findById(cropId);
    List<Fertilizer> fertilizers = crop.getFertilizers();
    return fertilizers.stream().map(FertilizerDto::fromEntity).toList();
  }
}
