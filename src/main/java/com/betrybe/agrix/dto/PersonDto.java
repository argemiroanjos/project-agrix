package com.betrybe.agrix.dto;

import com.betrybe.agrix.entity.Person;
import com.betrybe.agrix.security.Role;

/**
 * Representa um DTO para a entidade Person.
 */
public record PersonDto(
    Long id,
    String username,
    Role role
) {

  /**
   * Converte uma entidade Person para um DTO.
   */
  public static PersonDto fromEntity(Person person) {
    return new PersonDto(
        person.getId(),
        person.getUsername(),
        person.getRole()
    );
  }

  /**
   * Converte um DTO para uma entidade Person.
   */
  public static Person toEntity(PersonDto personDto) {
    Person person = new Person();
    person.setUsername(personDto.username());
    person.setRole(personDto.role());

    return person;
  }
}