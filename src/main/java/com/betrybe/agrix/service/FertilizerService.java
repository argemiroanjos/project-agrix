package com.betrybe.agrix.service;

import com.betrybe.agrix.entity.Fertilizer;
import com.betrybe.agrix.exception.FertilizerNotFoundException;
import com.betrybe.agrix.repository.FertilizerRepository;
import java.util.List;
import org.hibernate.FetchNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Fertilizer Service.
 */
@Service
public class FertilizerService {
  private final FertilizerRepository fertilizerRepository;

  @Autowired
  public FertilizerService(FertilizerRepository fertilizerRepository) {
    this.fertilizerRepository = fertilizerRepository;
  }

  public Fertilizer create(Fertilizer fertilizer) {
    return fertilizerRepository.save(fertilizer);
  }

  /**
   * Get all fertilizers.
   */
  public List<Fertilizer> getAllFertilizers() {
    return fertilizerRepository.findAll();
  }

  /**
   * Get fertilizer by id.
   */
  public Fertilizer getFertilizerById(Long id) throws FertilizerNotFoundException {
    return fertilizerRepository.findById(id).orElseThrow(FertilizerNotFoundException::new);
  }
}
