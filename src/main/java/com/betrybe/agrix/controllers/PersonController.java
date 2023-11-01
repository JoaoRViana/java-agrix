package com.betrybe.agrix.controllers;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Crop.
 */
@RestController
@RequestMapping(value = "/persons")
public class PersonController {
  @Autowired
  private PersonRepository personRepository;

  @PostMapping()
  public ResponseEntity<?> createPerson(@RequestBody Person person) {
    Person result = personRepository.save(person);
    return ResponseEntity.status(201).body(result);
  }
}
