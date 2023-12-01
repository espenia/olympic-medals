package com.tdd.grupo5.medallero.controller;

import com.tdd.grupo5.medallero.controller.dto.EventDTO;
import com.tdd.grupo5.medallero.controller.dto.EventLookupDTO;
import com.tdd.grupo5.medallero.entities.Event;
import com.tdd.grupo5.medallero.service.EventService;
import java.util.ArrayList;
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
      @RequestParam(required = false, value = "id") Long id,
      @RequestParam(required = false, value = "name") String name,
      @RequestParam(required = false, value = "category") String category,
      @RequestParam(required = false, value = "location") String location,
      @RequestParam(required = false, value = "date_from") String dateFrom,
      @RequestParam(required = false, value = "date_to") String dateTo,
      @RequestParam(required = false, value = "edition") Integer edition,
      @RequestParam(required = false, value = "athlete_first_name") String athleteFirstName,
      @RequestParam(required = false, value = "athlete_last_name") String athleteLastName) {
    EventLookupDTO eventLookup = new EventLookupDTO();

    if (id != null) {
      Event event = this.eventService.getEvent(id);
      List<EventDTO> list = new ArrayList<>();
      list.add(event.convertToDTO());
      eventLookup.setResults(list);
    } else {
      eventLookup =
          this.eventService.searchEvents(
              name == null || name.isBlank() ? null : name,
              category == null || category.isBlank() ? null : category,
              location == null || location.isBlank() ? null : location,
              dateFrom == null || dateFrom.isBlank() ? null : new Date(Date.parse(dateFrom)),
              dateTo == null || dateTo.isBlank() ? null : new Date(Date.parse(dateTo)),
              edition,
              athleteFirstName == null || athleteFirstName.isBlank() ? null : athleteFirstName,
              athleteLastName == null || athleteLastName.isBlank() ? null : athleteLastName);
    }
    return new ResponseEntity<>(eventLookup, HttpStatus.OK);
  }
}
