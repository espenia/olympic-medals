package com.tdd.grupo5.medallero.service;

import com.tdd.grupo5.medallero.controller.dto.AthleteDTO;
import com.tdd.grupo5.medallero.controller.dto.AthleteLookupDTO;
import com.tdd.grupo5.medallero.entities.Athlete;
import com.tdd.grupo5.medallero.repositories.AthleteRepository;
import com.tdd.grupo5.medallero.repositories.AthleteRepositoryCustom;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class AthleteService {

  private final AthleteRepositoryCustom athleteRespositoryCustom;
  private final AthleteRepository athleteRespository;

  public AthleteService(
      AthleteRepositoryCustom athleteRespositoryCustom, AthleteRepository athleteRespository) {
    this.athleteRespositoryCustom = athleteRespositoryCustom;
    this.athleteRespository = athleteRespository;
  }

  public Athlete createAthlete(AthleteDTO athleteDTO) {
    Athlete athlete =
        new Athlete(
            athleteDTO.getFirstName(),
            athleteDTO.getLastName(),
            athleteDTO.getCountry(),
            athleteDTO.getBirthDate(),
            athleteDTO.getGoldMedals(),
            athleteDTO.getSilverMedals(),
            athleteDTO.getBronzeMedals());
    athleteRespository.save(athlete);
    return athlete;
  }

  public AthleteLookupDTO searchAthletes(
      Long id,
      String firstName,
      String lastName,
      String country,
      Date birthDateFrom,
      Date birthDateTo) {
    List<Athlete> athletes =
        athleteRespositoryCustom.searchAthletes(
            id, firstName, lastName, country, birthDateFrom, birthDateTo);
    return new AthleteLookupDTO(
        athletes.stream().map(Athlete::convertDTO).collect(Collectors.toList()));
  }
}
