package com.tdd.grupo5.medallero.controller;

import com.tdd.grupo5.medallero.controller.dto.EventDTO;
import com.tdd.grupo5.medallero.entities.Event;
import com.tdd.grupo5.medallero.service.EventService;
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
  @PostMapping("backoffice/events")
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
}
