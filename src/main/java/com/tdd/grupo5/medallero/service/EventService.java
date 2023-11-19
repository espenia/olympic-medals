package com.tdd.grupo5.medallero.service;

import com.tdd.grupo5.medallero.controller.dto.ClassificationDTO;
import com.tdd.grupo5.medallero.controller.dto.EventDTO;
import com.tdd.grupo5.medallero.controller.dto.EventLookupDTO;
import com.tdd.grupo5.medallero.entities.Event;
import com.tdd.grupo5.medallero.repositories.EventRepository;
import com.tdd.grupo5.medallero.repositories.EventRepositoryCustom;
import com.tdd.grupo5.medallero.repositories.impl.EventRepositoryImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EventService {

  private final EventRepository eventRepository;
  private final EventRepositoryCustom eventRepositoryCustom;

  public EventService(EventRepository repository, EventRepositoryCustom eventRepositoryCustom) {

    this.eventRepository = repository;
    this.eventRepositoryCustom = eventRepositoryCustom;
  }

  public Event createEvent(EventDTO eventData) {

    Event newEvent =
        new Event(
            eventData.getName(),
            eventData.getEdition(),
            eventData.getParticipantCount(),
            eventData.getCategory(),
            eventData.getLocation(),
            eventData.getDescription(),
            eventData.getDate(),
            eventData.getDistance(),
            eventData.getOfficialSite(),
            eventData.getClassifications() == null
                ? new ArrayList<>()
                : eventData.getClassifications().stream()
                    .map(ClassificationDTO::convertToEntity)
                    .toList());

    this.eventRepository.save(newEvent);
    // TODO agregar para cada clasificacion notificacion para validar
    return newEvent;
  }

  public List<Event> getEvents() {

    return this.eventRepository.findAll();
  }

  public EventLookupDTO searchEvents(
      String name,
      String category,
      String location,
      Date dateFrom,
      Date dateTo,
      Integer edition,
      String athleteFirstName,
      String athleteLastName,
      String athleteCountry) {
    String query =
        EventRepositoryImpl.searchEvents(
            name,
            category,
            location,
            dateFrom,
            dateTo,
            edition,
            athleteFirstName,
            athleteLastName,
            athleteCountry);
    List<Event> events = this.eventRepositoryCustom.searchEvents(query);
    return new EventLookupDTO(events.stream().map(Event::convertToDTO).toList());
  }
}
