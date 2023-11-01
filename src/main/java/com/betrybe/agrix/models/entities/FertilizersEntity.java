package com.betrybe.agrix.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


/**
 * Farm.
 */
@Entity
public class FertilizersEntity {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  private Integer id;
  private String brand;
  private String name;
  private String composition;
  
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
  
  public String getBrand() {
    return this.brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }
  
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getComposition() {
    return composition;
  }

  public void setComposition(String composition) {
    this.composition = composition;
  }

}