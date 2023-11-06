package com.tdd.grupo5.medallero.service;

import com.tdd.grupo5.medallero.controller.dto.AthleteDTO;
import com.tdd.grupo5.medallero.entities.Athlete;
import com.tdd.grupo5.medallero.repositories.AthleteRepository;
import org.springframework.stereotype.Service;

@Service
public class AthleteService {

  private final AthleteRepository athleteRespository;

  public AthleteService(AthleteRepository athleteRespository) {
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
