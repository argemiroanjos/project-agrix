package com.betrybe.agrix.service;

import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.entity.Farm;
import com.betrybe.agrix.entity.Fertilizer;
import com.betrybe.agrix.exception.CropNotFoundException;
import com.betrybe.agrix.exception.FarmNotFoundException;
import com.betrybe.agrix.exception.FertilizerNotFoundException;
import com.betrybe.agrix.repository.CropRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Farm service.
 */
@Service
public class CropService {

  private final CropRepository cropRepository;
  private final FarmService farmService;
  private FertilizerService fertilizerService;


  /**
   * Instantiates a new Farm service.
   */
  @Autowired
  public CropService(
      CropRepository cropRepository,
      FarmService farmService,
      FertilizerService fertilizerService) {
    this.cropRepository = cropRepository;
    this.farmService = farmService;
    this.fertilizerService = fertilizerService;
  }

  /**
   * Create crop crop.
   */
  public Crop createCrop(Crop crop, Long farmId) throws FarmNotFoundException {
    Farm farm = farmService.getFarmById(farmId);
    crop.setFarm(farm);

    return cropRepository.save(crop);
  }

  /**
   * Get all crop list.
   */
  public List<Crop> getAllCrop() {
    return cropRepository.findAll();
  }

  public List<Crop> getCropById(Long farmId) throws CropNotFoundException {
    Farm farm = farmService.getFarmById(farmId);
    return farm.getCrops();
  }

  public Crop findById(Long id) throws CropNotFoundException {
    return cropRepository.findById(id).orElseThrow(CropNotFoundException::new);
  }

  public List<Crop> findByHarvestDateRange(LocalDate start, LocalDate end) {
    return cropRepository.findByHarvestDateBetween(start, end);
  }

  /**
   * Update crop fertilizer.
   */
  public Crop updateCropFertilizer(
      Long cropId, Long fertilizerId
  ) throws CropNotFoundException, FertilizerNotFoundException {
    Crop crop = findById(cropId);
    Fertilizer fertilizer = fertilizerService.getFertilizerById(fertilizerId);
    crop.getFertilizers().add(fertilizer);
    return cropRepository.save(crop);
  }

  /**
   * Find fertilizer by crop id list.
   */
  public List<Fertilizer> findFertilizerByCropId(Long cropId) throws CropNotFoundException {
    Crop crop = findById(cropId);
    return crop.getFertilizers();
  }
}
