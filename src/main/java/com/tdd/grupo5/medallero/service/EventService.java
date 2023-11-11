package com.tdd.grupo5.medallero.service;

import com.tdd.grupo5.medallero.controller.dto.EventDTO;
import com.tdd.grupo5.medallero.entities.Event;
import com.tdd.grupo5.medallero.repositories.EventRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

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
            eventData.getMaxParticipantCount(),
            eventData.getEventType(),
            eventData.getPlaceOfEvent(),
            eventData.getDescription(),
            eventData.getStartingDate(),
            eventData.getEndingDate());

    this.eventRepository.save(newEvent);
    return newEvent;
  }

  public List<Event> getEvents() {

    List<Event> events = this.eventRepository.findAll();

    return events;
  }

  public Event getEvent(String name) {

    Event event = this.eventRepository.findByName(name);

    return event;
  }

  public void changeEventState(String eventName) {

    Event event = this.eventRepository.findByName(eventName);

    if (!event.getName().isEmpty()) {

      event.changeStatus();
    }
  }

  public List<EventDTO> findFilteredEvents(MultiValueMap<String, String> filters) {

    List<EventDTO> events = new ArrayList<>();
    // TODO
    return events;
  }

  public EventDTO convertToDTO(Event event) {

    return EventDTO.builder()
        .name(event.getName())
        .maxParticipantCount(event.getMaxParticipantCount())
        .eventType(event.getEventType())
        .placeOfEvent(event.getPlaceOfEvent())
        .description(event.getDescription())
        .startingDate(event.getStartingDate())
        .endingDate(event.getEndingDate())
        .build();
  }

  public List<EventDTO> convertListToDTO(List<Event> events) {

    List<EventDTO> listOfEvents = new ArrayList<>(events.size());
    for (int i = 0; i < events.size(); i++) {

      listOfEvents.add(i, convertToDTO(events.get(i)));
    }

    return listOfEvents;
  }
}
