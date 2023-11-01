package com.betrybe.agrix.models.repository;

import com.betrybe.agrix.models.entities.CropEntity;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 * Farm.
 */
public interface CropRepository extends JpaRepository<CropEntity, Integer> {
  List<CropEntity> findByHarvestDateBetween(@Param("startDate") LocalDate startDate,
      @Param("endDate") LocalDate endDate);
}