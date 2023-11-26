package com.tdd.grupo5.medallero.unit;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

import com.tdd.grupo5.medallero.controller.dto.AthleteDTO;
import com.tdd.grupo5.medallero.controller.dto.AthleteLookupDTO;
import com.tdd.grupo5.medallero.entities.Athlete;
import com.tdd.grupo5.medallero.entities.Role;
import com.tdd.grupo5.medallero.entities.User;
import com.tdd.grupo5.medallero.repositories.AthleteRepository;
import com.tdd.grupo5.medallero.repositories.AthleteRepositoryCustom;
import com.tdd.grupo5.medallero.repositories.UserRepository;
import com.tdd.grupo5.medallero.service.AthleteService;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = NONE)
@ActiveProfiles("test")
class AthleteServiceTest {

  @Autowired private AthleteService athleteService;
  @Autowired private AthleteRepository athleteRepository;
  @Autowired private UserRepository userRepository;

  @Autowired private AthleteRepositoryCustom athleteRepositoryCustom;

  private final AthleteDTO athleteDTO =
      AthleteDTO.builder()
          .firstName("firstName")
          .lastName("lastName")
          .country("country")
          .birthDate(new Date())
          .bronzeMedals(0)
          .silverMedals(0)
          .goldMedals(0)
          .userName("userName")
          .build();

  @BeforeEach
  void setUp() {
    userRepository.deleteAll();
    athleteRepository.deleteAll();
    //        athleteRepository = Mockito.mock(AthleteRepository.class);
    //        athleteRepositoryCustom = Mockito.mock(AthleteRepositoryCustom.class);
    //        athleteService = new AthleteService(athleteRepositoryCustom, athleteRepository);
  }

  @Test
  void createAthletes() {
    // given
    // when
    userRepository.save(
        new User(
            "userName", "password", "first", "last", new Date(), "mail@mail.com", Role.Athlete));
    Athlete athlete = athleteService.createAthlete(athleteDTO);
    // then
    assert athlete.getId() != null;
    List<Athlete> athletes = athleteRepository.findAll();
    Assertions.assertEquals(athletes.get(0).getFirstName(), athlete.getFirstName());
    Assertions.assertEquals(athletes.get(0).getLastName(), athlete.getLastName());
    Assertions.assertEquals(athletes.get(0).getCountry(), athlete.getCountry());
    Assertions.assertEquals(
        athletes.get(0).getBirthDate().toInstant().toString(),
        athlete.getBirthDate().toInstant().toString());
  }

  @Test
  void searchAthletes() {
    // given
    createAthletes();
    // when
    AthleteLookupDTO results =
        athleteService.searchAthletes(
            null,
            "firstName",
            "lastName",
            "country",
            Date.from(athleteDTO.getBirthDate().toInstant().minus(13L, ChronoUnit.HOURS)),
            Date.from(athleteDTO.getBirthDate().toInstant().plus(13L, ChronoUnit.HOURS)),
            null);
    // then
    Assertions.assertEquals(1, results.getResults().size());
    Assertions.assertEquals(athleteDTO.getFirstName(), results.getResults().get(0).getFirstName());
    Assertions.assertEquals(athleteDTO.getLastName(), results.getResults().get(0).getLastName());
    Assertions.assertEquals(athleteDTO.getCountry(), results.getResults().get(0).getCountry());
  }
}
