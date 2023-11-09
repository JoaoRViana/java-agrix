package com.betrybe.agrix.controllers;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.service.JwtResponse;
import com.betrybe.agrix.ebytr.staff.service.LoginRequest;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import com.betrybe.agrix.ebytr.staff.service.TokenService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * persons.
 */
@RestController
public class PersonController {
  @Autowired
  AuthenticationManager authenticationManager;
  @Autowired
  private TokenService tokenService;
  @Autowired
  private PersonService personService;

  /**
   * any.
   */
  @PostMapping("/persons")
  public ResponseEntity<?> createPerson(@RequestBody Person person) {
    Person savePerson = personService.create(person);
    Object result = Map.of("username", savePerson.getUsername(),
        "id", savePerson.getId(), "role", savePerson.getRole());

    return ResponseEntity.status(201).body(result);
  }

  /**
   * any.
   */
  @PostMapping("/auth/login")
  public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
    UsernamePasswordAuthenticationToken usernamePassword =
        new UsernamePasswordAuthenticationToken(
            loginRequest.getUsername(),
            loginRequest.getPassword());

    Authentication auth = authenticationManager.authenticate(usernamePassword);
    UserDetails user = (UserDetails) auth.getPrincipal();

    String genToken = tokenService.generateToken(user);

    JwtResponse result = new JwtResponse(genToken);

    return ResponseEntity.status(200).body(result);
  }
}
