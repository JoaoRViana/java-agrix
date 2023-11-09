package com.betrybe.agrix.controllers;

import com.betrybe.agrix.models.entities.FertilizersEntity;
import com.betrybe.agrix.models.repository.FertilizerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Crop.
 */
@RestController
@RequestMapping(value = "/fertilizers")
public class FertilizersController {
  @Autowired
  private FertilizerRepository fertilizerRepository;

  @PostMapping()
  public ResponseEntity<?> createFertilizy(@RequestBody FertilizersEntity fertilizy) {
    FertilizersEntity result = fertilizerRepository.save(fertilizy);
    return ResponseEntity.status(201).body(result);
  }

  @GetMapping()
  @Secured({"ADMIN"})
  public ResponseEntity<?> getFertilizies() {
    List<FertilizersEntity> result = fertilizerRepository.findAll();
    return ResponseEntity.status(200).body(result);
  }

  /**
   * any.
   */ 

  @GetMapping("/{id}")
  public ResponseEntity<?> getFertilizy(@PathVariable Integer id) {
    Optional<FertilizersEntity> result = fertilizerRepository.findById(id);
    if (result.isPresent()) {
      return ResponseEntity.status(200).body(result);
    }
    return ResponseEntity.status(404).body("Fertilizante não encontrado!");
  }
}
