package com.betrybe.agrix.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Farm.
 */
@Entity
public class FarmEntity {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  private Integer id;
  private Double size;
  private String name;
  
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
  
  public Double getSize() {
    return this.size;
  }

  public void setSize(Double size) {
    this.size = size;
  }
  
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}