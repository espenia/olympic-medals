package com.tdd.grupo5.medallero.controller;

import com.tdd.grupo5.medallero.service.EventService;
import com.tdd.grupo5.medallero.controller.dto.EventDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventController {

    private final EventService eventService;

    public EventController(EventService service){

        this.eventService = service;

    }

    @RequestMapping("/backoffice")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/events")
    public ResponseEntity<EventDTO> createEvent(@RequestBody EventDTO eventData){
        EventDTO createdEvent = this.eventService.createEvent(eventData);
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }

    @RequestMapping("/api")
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/events")
    public ResponseEntity<List<EventDTO>> getEvents(){
        List<EventDTO> events = this.eventService.getEvent();
        return  new ResponseEntity<>(events, HttpStatus.FOUND);
    }

    @RequestMapping("/backoffice")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping ("/events/{eventName}")
    public void updateStatusFor(@PathVariable String eventName){
        this.eventService.changeEventState(eventName);
    }

}
