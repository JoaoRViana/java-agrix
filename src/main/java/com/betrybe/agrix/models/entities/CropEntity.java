package com.betrybe.agrix.models.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.List;


/**
 * Crop.
 */
@Entity
public class CropEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "farm_id")
  @JsonIgnore
  private FarmEntity farmEntity;


  @ManyToMany
  @JoinTable(
          name = "crop_fert",
          joinColumns = @JoinColumn(name = "fertiliziers_id"),
          inverseJoinColumns = @JoinColumn(name = "crop_id")
  )
  private List<FertilizersEntity> fertilizers;

  private String name;
  private Double plantedArea;
  private LocalDate plantedDate;
  private LocalDate harvestDate;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getPlantedArea() {
    return plantedArea;
  }

  public void setPlantedArea(Double plantedArea) {
    this.plantedArea = plantedArea;
  }

  public Integer getFarmId() {
    return farmEntity.getId();
  }

  public void setFarm(FarmEntity farmEntity) {
    this.farmEntity = farmEntity;
  }

  public void setPlantedDate(LocalDate date) {
    this.plantedDate = date;
  }

  public LocalDate getPlantedDate() {
    return plantedDate;
  }

  public void setHarvestDate(LocalDate date) {
    this.harvestDate = date;
  }

  public LocalDate getHarvestDate() {
    return harvestDate;
  }

  public List<FertilizersEntity> giussepe() {
    return this.fertilizers;
  }

}