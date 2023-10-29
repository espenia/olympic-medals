package com.tdd.grupo5.medallero.service;

import com.tdd.grupo5.medallero.controller.dto.AthleteDTO;
import com.tdd.grupo5.medallero.entities.Athlete;
import com.tdd.grupo5.medallero.repositories.AthleteRespository;
import org.springframework.stereotype.Service;

@Service
public class AthleteService {

  private final AthleteRespository athleteRespository;

  public AthleteService(AthleteRespository athleteRespository) {
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
}
