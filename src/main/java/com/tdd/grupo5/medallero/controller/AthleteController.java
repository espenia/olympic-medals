package com.tdd.grupo5.medallero.controller;

import com.tdd.grupo5.medallero.controller.dto.AthleteDTO;
import com.tdd.grupo5.medallero.service.AthleteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AthleteController {

  private final AthleteService athleteService;

  public AthleteController(AthleteService athleteService) {
    this.athleteService = athleteService;
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("/athletes")
  public ResponseEntity<AthleteDTO> createAthlete(@RequestBody AthleteDTO athleteDTO) {
    AthleteDTO athlete = athleteService.createAthlete(athleteDTO).convertDTO();
    return new ResponseEntity<>(athlete, HttpStatus.CREATED);
  }
}
