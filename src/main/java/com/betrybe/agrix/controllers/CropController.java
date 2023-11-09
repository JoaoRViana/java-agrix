package com.betrybe.agrix.controllers;

import com.betrybe.agrix.models.entities.CropEntity;
import com.betrybe.agrix.models.entities.FertilizersEntity;
import com.betrybe.agrix.models.repository.CropRepository;
import com.betrybe.agrix.models.repository.FertilizerRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Crop.
 */
@RestController
@RequestMapping(value = "/crops")
public class CropController {
  @Autowired
  private CropRepository cropRepository;
  @Autowired
  private FertilizerRepository fertilizerRepository;

  @GetMapping()
  @Secured({"MANAGER", "ADMIN"})
  public ResponseEntity<?> getAllCrops() {
    List<CropEntity> crops = cropRepository.findAll();
    return ResponseEntity.status(200).body(crops);
  }

  /**
   * any.
   */
  @GetMapping("/{id}")
  public ResponseEntity<?> getFarm(@PathVariable Integer id) {
    Optional<CropEntity> result = cropRepository.findById(id);
    if (result.isPresent()) {
      return ResponseEntity.status(200).body(result);
    } else {
      return ResponseEntity.status(404).body("Plantação não encontrada!");
    }
  }

  /**
   * any.
   */
  @GetMapping("/search")
  public ResponseEntity<?> searchFarm(@RequestParam LocalDate start, 
      @RequestParam LocalDate end) {
    List<CropEntity> result = cropRepository.findByHarvestDateBetween(start, end);
    return ResponseEntity.status(200).body(result);
  }

  /**
   * any.
   */

  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
    public ResponseEntity<?> getFertilizy(@PathVariable Integer cropId, 
      @PathVariable Integer fertilizerId) {
    CropEntity crop  = cropRepository.findById(cropId).orElse(null);
    FertilizersEntity fertilizy = fertilizerRepository.findById(fertilizerId).orElse(null);
    if (crop == null) {
      return ResponseEntity.status(404).body("Plantação não encontrada!");
    }
    if (fertilizy == null) {
      return ResponseEntity.status(404).body("Fertilizante não encontrado!");
    }

    List<FertilizersEntity> fertilizers = crop.giussepe();
    fertilizers.add(fertilizy);
    cropRepository.save(crop);
    return ResponseEntity.status(201).body("Fertilizante e plantação associados com sucesso!");
  }

  /**
   * any.
   */

  @GetMapping("/{cropId}/fertilizers")
    public ResponseEntity<?> getFertilizers(@PathVariable Integer cropId) {
    CropEntity crop  = cropRepository.findById(cropId).orElse(null);
    if (crop == null) {
      return ResponseEntity.status(404).body("Plantação não encontrada!");
    }
    List<FertilizersEntity> fertilizers = crop.giussepe();
    return ResponseEntity.status(200).body(fertilizers);
  }
}
