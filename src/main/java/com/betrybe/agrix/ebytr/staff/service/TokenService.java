package com.betrybe.agrix.ebytr.staff.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.betrybe.agrix.ebytr.staff.entity.Person;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * aaaa.
 */
@Service
public class TokenService {

  @Value("Jao")
  private String secret;
  /**
  * aaaa.
  */

  public String generateToken(Person person) {
    Algorithm algorithm = Algorithm.HMAC256(secret);
    return JWT.create()
              .withIssuer("trybetrack")
              .withSubject(person.getUsername())
              .withExpiresAt(generateExpirationDate())
              .sign(algorithm);
  }

  private Instant generateExpirationDate() {
    return LocalDateTime.now()
          .plusHours(2)
          .toInstant(ZoneOffset.of("-03:00"));
  }
}
