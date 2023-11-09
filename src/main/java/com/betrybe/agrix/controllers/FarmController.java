package com.betrybe.agrix.controllers;

import com.betrybe.agrix.models.entities.CropEntity;
import com.betrybe.agrix.models.entities.FarmEntity;
import com.betrybe.agrix.models.repository.CropRepository;
import com.betrybe.agrix.models.repository.FarmRepository;
import java.util.ArrayList;
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
 * Farm.
 */
@RestController
@RequestMapping(value = "/farms")
public class FarmController {
  @Autowired 
  private FarmRepository farmRepository;
  @Autowired
  private CropRepository cropRepository;

  @PostMapping
  public ResponseEntity<FarmEntity> createFarm(@RequestBody FarmEntity farmEntity) {
    FarmEntity result = farmRepository.save(farmEntity);
    return ResponseEntity.status(201).body(result);
  }

  @GetMapping
  @Secured({"MANAGER", "ADMIN", "USER"})
  public List<FarmEntity> getFarms() {
    List<FarmEntity> result = farmRepository.findAll();
    return result;
  }

  /**
   * any.
   */
  @GetMapping("/{id}")
  public ResponseEntity<?> getFarm(@PathVariable Integer id) {
    Optional<FarmEntity> result = farmRepository.findById(id);
    
    if (result.isPresent()) {
      return ResponseEntity.status(200).body(result);
    } else {
      return ResponseEntity.status(404).body("Fazenda não encontrada!");
    }
  }

  /**
   * any.
   */
  @PostMapping("/{farmId}/crops")
  public ResponseEntity<?> createCrop(@PathVariable Integer farmId, @RequestBody CropEntity crop) {
    FarmEntity farm = farmRepository.findById(farmId).orElse(null);
    if (farm == null) {
      return ResponseEntity.status(404).body("Fazenda não encontrada!");
    } else {
      crop.setFarm(farm);
      CropEntity result = cropRepository.save(crop);
      return ResponseEntity.status(201).body(result);
    }
  }

  /**
   * any.
   */
  @GetMapping("/{farmId}/crops")
  public ResponseEntity<?> getCrops(@PathVariable Integer farmId) {
    List<CropEntity> crops = cropRepository.findAll();
    List<CropEntity> result = new ArrayList<>();
    for (int i = 0; i < crops.size(); i++) {
      if (crops.get(i).getFarmId() == farmId) {
        result.add(crops.get(i));
      }
    }
    FarmEntity farm = farmRepository.findById(farmId).orElse(null);
    if (farm == null) {
      return ResponseEntity.status(404).body("Fazenda não encontrada!");
    } else {
      return ResponseEntity.status(200).body(result);
    }
  }

}