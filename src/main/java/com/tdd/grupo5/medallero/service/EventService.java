package com.tdd.grupo5.medallero.service;

import com.tdd.grupo5.medallero.controller.dto.EventDTO;
import com.tdd.grupo5.medallero.entities.Event;
import com.tdd.grupo5.medallero.repositories.EventRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EventService {

  private final EventRepository eventRepository;

  public EventService(EventRepository repository) {

    this.eventRepository = repository;
  }

  public Event createEvent(EventDTO eventData) {

    Event newEvent =
        new Event(
            eventData.getName(),
            eventData.getParticipantCount(),
            eventData.getCategory(),
            eventData.getLocation(),
            eventData.getDescription(),
            eventData.getDate(),
            eventData.getEdition(),
            eventData.getOfficialSite());

    this.eventRepository.save(newEvent);
    return newEvent;
  }

  public List<Event> getEvents() {

    return this.eventRepository.findAll();
  }
}
