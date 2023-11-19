package com.tdd.grupo5.medallero.controller;

import com.tdd.grupo5.medallero.controller.dto.EventDTO;
import com.tdd.grupo5.medallero.controller.dto.EventLookupDTO;
import com.tdd.grupo5.medallero.entities.Event;
import com.tdd.grupo5.medallero.service.EventService;
import java.time.Instant;
import java.util.Date;
import java.util.List;
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
      @RequestParam(required = false, value = "date_from") String dateFrom,
      @RequestParam(required = false, value = "date_to") String dateTo,
      @RequestParam(required = false, value = "edition") Integer edition,
      @RequestParam(required = false, value = "athlete_first_name") String athleteFirstName,
      @RequestParam(required = false, value = "athlete_last_name") String athleteLastName,
      @RequestParam(required = false, value = "athlete_country") String athleteCountry) {
    EventLookupDTO eventLookup =
        this.eventService.searchEvents(
            name,
            category,
            location,
            dateFrom == null ? null : Date.from(Instant.parse(dateFrom)),
            dateTo == null ? null : Date.from(Instant.parse(dateTo)),
            edition,
            athleteFirstName,
            athleteLastName,
            athleteCountry);
    return new ResponseEntity<>(eventLookup, HttpStatus.OK);
  }
}
