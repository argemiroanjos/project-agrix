package com.betrybe.agrix.service;

import com.betrybe.agrix.entity.Farm;
import com.betrybe.agrix.exception.FarmNotFoundException;
import com.betrybe.agrix.repository.FarmRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * The type Farm service.
 */
@Service
public class FarmService {
  private final FarmRepository farmRepository;

  /**
   * Instantiates a new Farm service.
   */
  @Autowired
  public FarmService(FarmRepository farmRepository) {
    this.farmRepository = farmRepository;
  }

  /**
   * Create farm.
   */
  public Farm createFarm(Farm farm) {
    return farmRepository.save(farm);
  }

  /**
   * Get all farm.
   */
  public List<Farm> getAllFarm() {
    return farmRepository.findAll();
  }

  /**
   * Get farm by id.
   */
  public Farm getFarmById(Long id) throws FarmNotFoundException {
    return farmRepository.findById(id).orElseThrow(FarmNotFoundException::new);
  }
}
