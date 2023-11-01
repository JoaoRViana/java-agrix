package com.betrybe.agrix.models.repository;

import com.betrybe.agrix.models.entities.FarmEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Farm.
 */
public interface FarmRepository extends JpaRepository<FarmEntity, Integer> {
    
}