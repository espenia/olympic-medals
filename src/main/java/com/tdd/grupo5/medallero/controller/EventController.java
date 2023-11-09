package com.tdd.grupo5.medallero.controller;

import com.tdd.grupo5.medallero.controller.dto.EventDTO;
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
    EventDTO createdEvent = this.eventService.createEvent(eventData);
    return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
  }

  @ResponseStatus(HttpStatus.FOUND)
  @GetMapping("api/events")
  public ResponseEntity<List<EventDTO>> getEvents() {
    List<EventDTO> events = this.eventService.getEvents();
    return new ResponseEntity<>(events, HttpStatus.FOUND);
  }

  @ResponseStatus(HttpStatus.OK)
  @PatchMapping("/backoffice/events/{eventName}")
  public void updateStatusFor(@PathVariable String eventName) {
    this.eventService.changeEventState(eventName);
  }

  @ResponseStatus(HttpStatus.FOUND)
  @GetMapping("api/events/{eventName}")
  public ResponseEntity<EventDTO> getEvent(@PathVariable String eventName) {
    EventDTO event = this.eventService.getEvent(eventName);
    return new ResponseEntity<>(event, HttpStatus.FOUND);
  }

  // Filtrado de eventos de la forma "key:value" dentro del parametro "filter"
  // Ej: medallero/api/events/?filter=key:value,key2:value2
  //  @ResponseStatus(HttpStatus.OK)
  //  @GetMapping("/api/events")
  //  public ResponseEntity<List<EventDTO>> filterEvents(@RequestParam MultiValueMap<String, String>
  // filters){
  //    List<EventDTO> events = this.eventService.findFilteredEvents(filters);
  //    return new ResponseEntity<> (events, HttpStatus.OK);
  //  }
}
