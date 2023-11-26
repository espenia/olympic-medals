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

  // Devuelve todas las clasificaciones guardadas en eventos creados que cumplan con los
  // parametros pasados por query.
  // Si el parametro "athleteId" es null -> devuelve aquellas clasificaciones que requieren
  // la aprobacion del atleta en cuestion para afirmar que la clasificacion le pertenece a este
  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/api/classifications/search")
  public ResponseEntity<ClassificationLookupDTO> getClassifications(
      @RequestParam(required = false, value = "athlete_first_name") String athleteFirstName,
      @RequestParam(required = false, value = "athlete_last_name") String athleteLastName,
      @RequestParam(required = false, value = "athlete_id") Long athleteId,
      @RequestParam(required = false, value = "event_name") String eventName,
      @RequestParam(required = false, value = "event_id") Long eventId) {

    ClassificationLookupDTO classifications;
    if (athleteId == null) {
      classifications =
          this.classificationService.getUnassignedClassifications(
              athleteFirstName, athleteLastName, eventName, eventId);
    } else {
      classifications =
          this.classificationService.getAssignedClassifications(
              athleteFirstName, athleteLastName, athleteId, eventName, eventId);
    }

    return new ResponseEntity<>(classifications, HttpStatus.OK);
  }
}
