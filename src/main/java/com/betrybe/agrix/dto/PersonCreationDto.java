package com.betrybe.agrix.dto;

import com.betrybe.agrix.entity.Person;
import com.betrybe.agrix.security.Role;

/**
 * Representa um DTO para a entidade Person.
 */
public record PersonCreationDto(Long id, String username, String password, Role role) {

  /**
   * Converte um DTO para uma entidade Person.
   */
  public Person toEntity() {
    Person person = new Person();
    person.setUsername(username);
    person.setPassword(password);
    person.setRole(role);

    return person;
  }
}