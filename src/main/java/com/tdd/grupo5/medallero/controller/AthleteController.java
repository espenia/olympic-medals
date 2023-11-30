package com.tdd.grupo5.medallero.controller;

import com.tdd.grupo5.medallero.controller.dto.AthleteDTO;
import com.tdd.grupo5.medallero.controller.dto.AthleteLookupDTO;
import com.tdd.grupo5.medallero.entities.User;
import com.tdd.grupo5.medallero.service.AthleteService;
import com.tdd.grupo5.medallero.service.UserService;
import java.time.Instant;
import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AthleteController {

  private final AthleteService athleteService;
  private final UserService userService;

  public AthleteController(AthleteService athleteService, UserService userService) {
    this.athleteService = athleteService;
    this.userService = userService;
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
      @RequestParam(required = false, value = "id") Long id,
      @RequestParam(required = false, value = "first_name") String firstName,
      @RequestParam(required = false, value = "last_name") String lastName,
      @RequestParam(required = false, value = "country") String country,
      @RequestParam(required = false, value = "birth_date_from") String birthDateFrom,
      @RequestParam(required = false, value = "birth_date_to") String birthDateTo,
      @RequestParam(required = false, value = "mail") String userMail) {
    AthleteLookupDTO athlete =
        athleteService.searchAthletes(
            id,
            firstName.isBlank() ? null : firstName,
            lastName.isBlank() ? null : lastName,
            country.isBlank() ? null : country,
            birthDateFrom == null || birthDateFrom.isBlank()
                ? null
                : Date.from(Instant.parse(birthDateFrom)),
            birthDateTo == null || birthDateTo.isBlank()
                ? null
                : Date.from(Instant.parse(birthDateTo)),
            userMail.isBlank() ? null : userMail);
    return new ResponseEntity<>(athlete, HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/athlete")
  public ResponseEntity<AthleteDTO> getAthlete(@RequestHeader(name = "X-Auth-Token") String token) {
    User user = userService.getUserByToken(token);
    AthleteDTO athlete = athleteService.getAthlete(user.getMail()).convertDTO();
    return new ResponseEntity<>(athlete, HttpStatus.OK);
  }
}
