package com.tdd.grupo5.medallero.service;

import com.tdd.grupo5.medallero.controller.dto.ClassificationDTO;
import com.tdd.grupo5.medallero.controller.dto.EventDTO;
import com.tdd.grupo5.medallero.controller.dto.EventLookupDTO;
import com.tdd.grupo5.medallero.entities.Event;
import com.tdd.grupo5.medallero.repositories.EventRepository;
import com.tdd.grupo5.medallero.repositories.EventRepositoryCustom;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
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
            eventData.getParticipantCount(),
            eventData.getCategory(),
            eventData.getLocation(),
            eventData.getDescription(),
            eventData.getDate(),
            eventData.getEdition(),
            eventData.getOfficialSite(),
            eventData.getClassifications().stream()
                .map(ClassificationDTO::convertToEntity)
                .toList());

    this.eventRepository.save(newEvent);
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
    List<Event> events =
        this.eventRepositoryCustom.searchEvents(
            name,
            category,
            location,
            dateFrom,
            dateTo,
            edition,
            athleteFirstName,
            athleteLastName,
            athleteCountry);
    return new EventLookupDTO(events.stream().map(Event::convertToDTO).toList());
  }

  public void requestAdd(UUID id, ClassificationDTO classification) {

    Optional<Event> node = this.eventRepository.findById(id);
    if (node.isPresent()) {
      Event event = node.get();
      event.add(classification.convertToEntity());
      this.eventRepository.save(event);
    } else {
      throw new RuntimeException("Error: El evento requerido no se encuentro");
    }
  }
}
