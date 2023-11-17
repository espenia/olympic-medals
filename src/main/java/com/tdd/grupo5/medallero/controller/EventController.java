package com.tdd.grupo5.medallero.controller;

import com.tdd.grupo5.medallero.controller.dto.ClassificationDTO;
import com.tdd.grupo5.medallero.controller.dto.EventDTO;
import com.tdd.grupo5.medallero.controller.dto.EventLookupDTO;
import com.tdd.grupo5.medallero.entities.Event;
import com.tdd.grupo5.medallero.service.EventService;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EventController {

  private final EventService eventService;

  public EventController(EventService service) {

    this.eventService = service;
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("backoffice/event")
  public ResponseEntity<EventDTO> createEvent(@RequestBody EventDTO eventData) {
    EventDTO createdEvent = this.eventService.createEvent(eventData).convertToDTO();
    return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("api/events")
  public ResponseEntity<List<EventDTO>> getEvents() {
    List<Event> events = this.eventService.getEvents();
    return new ResponseEntity<>(events.stream().map(Event::convertToDTO).toList(), HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("api/events/search")
  public ResponseEntity<EventLookupDTO> search(
      @RequestParam(required = false, value = "name") String name,
      @RequestParam(required = false, value = "category") String category,
      @RequestParam(required = false, value = "location") String location,
      @RequestParam(required = false, value = "date_from") Date dateFrom,
      @RequestParam(required = false, value = "date_to") Date dateTo,
      @RequestParam(required = false, value = "edition") Integer edition,
      @RequestParam(required = false, value = "athlete_first_name") String athleteFirstName,
      @RequestParam(required = false, value = "athlete_last_name") String athleteLastName,
      @RequestParam(required = false, value = "athlete_country") String athleteCountry) {
    EventLookupDTO eventLookup =
        this.eventService.searchEvents(
            name,
            category,
            location,
            dateFrom,
            dateTo,
            edition,
            athleteFirstName,
            athleteLastName,
            athleteCountry);
    return new ResponseEntity<>(eventLookup, HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.OK)
  @PostMapping("api/events/{id}/classification")
  public ResponseEntity<String> postClassification(
      @PathVariable UUID id, @RequestBody ClassificationDTO classification) {

    // Como esta clasificacion es propuesta por un atleta -> el campo
    // "pendingValidation" debe ser un "true"
    if (!classification.isPendingValidation()) {
      return new ResponseEntity<>(
          "la clasificacion dada debe provenir de un atleta", HttpStatus.NOT_ACCEPTABLE);
    }
    this.eventService.requestAdd(id, classification);
    return new ResponseEntity<>("todo bien", HttpStatus.OK);
  }
}
