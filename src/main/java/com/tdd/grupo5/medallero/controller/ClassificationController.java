package com.tdd.grupo5.medallero.controller;

import com.tdd.grupo5.medallero.controller.dto.ClassificationLookupDTO;
import com.tdd.grupo5.medallero.service.ClassificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClassificationController {

  private ClassificationService classificationService;

  public ClassificationController(ClassificationService service) {

    this.classificationService = service;
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/api/classifications/search")
  public ResponseEntity<ClassificationLookupDTO> search(
      @RequestParam(name = "event_name", required = false) String eventName,
      @RequestParam(name = "athlete_first_name", required = false) String athleteFirstName,
      @RequestParam(name = "athlete_last_name", required = false) String athleteLastName,
      @RequestParam(name = "user_id", required = false) Long userId) {

    ClassificationLookupDTO classifications =
        this.classificationService.search(eventName, athleteFirstName, athleteLastName, userId);
    return new ResponseEntity<>(classifications, HttpStatus.OK);
  }
}
