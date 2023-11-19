package com.tdd.grupo5.medallero.controller;

import com.tdd.grupo5.medallero.controller.dto.AthleteDTO;
import com.tdd.grupo5.medallero.controller.dto.AthleteLookupDTO;
import com.tdd.grupo5.medallero.service.AthleteService;
import java.time.Instant;
import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
  @PostMapping("/athlete")
  public ResponseEntity<AthleteDTO> createAthlete(@RequestBody AthleteDTO athleteDTO) {
    AthleteDTO athlete = athleteService.createAthlete(athleteDTO).convertDTO();
    return new ResponseEntity<>(athlete, HttpStatus.CREATED);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/athletes")
  public ResponseEntity<AthleteLookupDTO> searchAthletes(
      @RequestParam(required = false, value = "first_name") String firstName,
      @RequestParam(required = false, value = "last_name") String lastName,
      @RequestParam(required = false, value = "country") String country,
      @RequestParam(required = false, value = "birth_date_from") String birthDateFrom,
      @RequestParam(required = false, value = "birth_date_to") String birthDateTo) {
    AthleteLookupDTO athlete =
        athleteService.searchAthletes(
            firstName,
            lastName,
            country,
            birthDateFrom == null ? null : Date.from(Instant.parse(birthDateFrom)),
            birthDateTo == null ? null : Date.from(Instant.parse(birthDateTo)));
    return new ResponseEntity<>(athlete, HttpStatus.OK);
  }
}
