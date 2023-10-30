package com.tdd.grupo5.medallero.service;

import com.tdd.grupo5.medallero.controller.dto.AthleteDTO;
import com.tdd.grupo5.medallero.controller.dto.AthleteLookupDTO;
import com.tdd.grupo5.medallero.entities.Athlete;
import com.tdd.grupo5.medallero.repositories.AthleteRespository;
import com.tdd.grupo5.medallero.repositories.AthleteRespositoryCustom;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class AthleteService {

  private final AthleteRespositoryCustom athleteRespositoryCustom;
  private final AthleteRespository athleteRespository;

  public AthleteService(
      AthleteRespositoryCustom athleteRespositoryCustom, AthleteRespository athleteRespository) {
    this.athleteRespositoryCustom = athleteRespositoryCustom;
    this.athleteRespository = athleteRespository;
  }

  public Athlete createAthlete(AthleteDTO athleteDTO) {
    Athlete athlete =
        new Athlete(
            athleteDTO.getFirstName(),
            athleteDTO.getLastName(),
            athleteDTO.getCountry(),
            athleteDTO.getBirthDate());
    athleteRespository.save(athlete);
    return athlete;
  }

  public AthleteLookupDTO searchAthletes(
      String firstName, String lastName, String country, Date birthDateFrom, Date birthDateTo) {
    List<Athlete> athletes =
        athleteRespositoryCustom.searchAthletes(
            firstName, lastName, country, birthDateFrom, birthDateTo);
    return new AthleteLookupDTO(
        athletes.stream().map(Athlete::convertDTO).collect(Collectors.toList()));
  }
}
