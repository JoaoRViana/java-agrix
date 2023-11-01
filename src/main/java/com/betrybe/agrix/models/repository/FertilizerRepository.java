package com.betrybe.agrix.models.repository;

import com.betrybe.agrix.models.entities.FertilizersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Fertilizy.
 */
public interface FertilizerRepository extends JpaRepository<FertilizersEntity, Integer> {
    
}
