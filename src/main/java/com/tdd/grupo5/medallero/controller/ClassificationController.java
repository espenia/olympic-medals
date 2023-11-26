package com.tdd.grupo5.medallero.controller;

import com.tdd.grupo5.medallero.controller.dto.ClassificationDTO;
import com.tdd.grupo5.medallero.controller.dto.ClassificationLookupDTO;
import com.tdd.grupo5.medallero.controller.dto.UserDTO;
import com.tdd.grupo5.medallero.service.ClassificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClassificationController {

  private final ClassificationService classificationService;

  public ClassificationController(final ClassificationService service) {

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

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/api/classifications/{id}/accept")
  public ResponseEntity<ClassificationDTO> accept(
      @PathVariable final Long id, @RequestBody final UserDTO user) {
    ClassificationDTO classification =
        classificationService.acceptClassification(id, user).convertToDTO();
    return new ResponseEntity<>(classification, HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/api/classifications/{id}/reject")
  public ResponseEntity<Object> reject(@PathVariable Long id) {
    classificationService.rejectClassification(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
