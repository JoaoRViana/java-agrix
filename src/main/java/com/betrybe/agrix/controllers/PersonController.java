package com.betrybe.agrix.controllers;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.repository.PersonRepository;
import com.betrybe.agrix.ebytr.staff.service.JwtResponse;
import com.betrybe.agrix.ebytr.staff.service.LoginRequest;
import com.betrybe.agrix.ebytr.staff.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * persons.
 */
@RestController
public class PersonController {
  @Autowired
  private PersonRepository personRepository;
  @Autowired
  private TokenService tokenService;

  /**
   * any.
   */
  @PostMapping("/persons")
  public ResponseEntity<?> createPerson(@RequestBody Person person) {
    Person result = personRepository.save(person);
    return ResponseEntity.status(201).body(result);
  }

  /**
   * any.
   */
  @PostMapping("/auth/login")
  public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
    Person user = personRepository.findByUsername(loginRequest.getUsername()).orElse(null);
    if (user != null && user.giuseppe().equals(loginRequest.getPassword())) {
      String token = tokenService.generateToken(user);
      JwtResponse result = new JwtResponse(token);
      return ResponseEntity.status(200).body(result);
    } else {
      return ResponseEntity.status(403).body(null);
    }
  }
}
