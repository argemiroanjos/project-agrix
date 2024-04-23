package com.betrybe.agrix.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import com.betrybe.agrix.entity.Person;
import com.betrybe.agrix.exception.PersonNotFoundException;
import com.betrybe.agrix.repository.PersonRepository;
import com.betrybe.agrix.service.PersonService;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

/**
 * Tests for PersonService Class.
 */
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@DisplayName("Tests to verify Person Service")
public class PersonServiceTest {
  @Autowired
  PersonService personService;

  @MockBean
  PersonRepository personRepository;

  private static final Person ironMan = new Person();
  private static final Person captainAmerica = new Person();

  static {
    ironMan.setId(1L);
    ironMan.setUsername("Tony Stark");

    captainAmerica.setId(2L);
    captainAmerica.setUsername("Steve Rogers");
  }

  @Test
  public void testGetPersonById() {
    Mockito.when(personRepository.findById(1L)).thenReturn(Optional.of(ironMan));

    Person personFoundById = personService.getPersonById(1L);

    assertEquals(personFoundById.getId(), 1L);
    assertEquals(personFoundById.getUsername(), "Tony Stark");
  }

  @Test
  public void testGetPersonByName() {
    Mockito.when(personRepository.findByUsername("Tony Stark")).thenReturn(Optional.of(ironMan));

    Person personFoundByName = personService.getPersonByUsername("Tony Stark");

    assertEquals(personFoundByName.getId(), 1L);
    assertEquals(personFoundByName.getUsername(), "Tony Stark");
  }

  @Test
  public void testCreatePerson() {
    Mockito.when(personRepository.save(any())).thenReturn(captainAmerica);

    Person personCreated = personService.create(captainAmerica);

    assertEquals(personCreated.getId(), 2L);
    assertEquals(personCreated.getUsername(), "Steve Rogers");
  }

  @Test
  public void testGetPersonByIdNotFound() {
    Mockito.when(personRepository.findById(any())).thenReturn(Optional.empty());

    assertThrows(PersonNotFoundException.class, () -> personService.getPersonById(9999L));
  }

  @Test
  public void testGetPersonByNameNotFound() {
    Mockito.when(personRepository.findByUsername(any())).thenReturn(Optional.empty());

    assertThrows(PersonNotFoundException.class,
        () -> personService.getPersonByUsername("Steve Rogers"));
  }
}
