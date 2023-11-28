package com.tdd.grupo5.medallero.service;

import com.tdd.grupo5.medallero.controller.dto.ClassificationDTO;
import com.tdd.grupo5.medallero.controller.dto.EventDTO;
import com.tdd.grupo5.medallero.controller.dto.EventLookupDTO;
import com.tdd.grupo5.medallero.entities.Classification;
import com.tdd.grupo5.medallero.entities.Event;
import com.tdd.grupo5.medallero.repositories.ClassificationRepository;
import com.tdd.grupo5.medallero.repositories.EventRepository;
import com.tdd.grupo5.medallero.repositories.EventRepositoryCustom;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EventService {

  private final EventRepository eventRepository;
  private final ClassificationRepository classificationRepository;
  private final EventRepositoryCustom eventRepositoryCustom;

  public EventService(
      EventRepository repository,
      ClassificationRepository classificationRepository,
      EventRepositoryCustom eventRepositoryCustom) {

    this.eventRepository = repository;
    this.classificationRepository = classificationRepository;
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
            eventData.getOfficialSite());

    this.eventRepository.save(newEvent);
    List<Classification> classifications = null;
    if (eventData.getClassifications() != null) {
      classifications =
          eventData.getClassifications().stream().map(ClassificationDTO::convertToEntity).toList();
      classifications.forEach((c) -> c.setEvent(newEvent));
      this.classificationRepository.saveAll(classifications);
    }
    newEvent.setClassifications(classifications);
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
      String athleteLastName) {
    List<Event> events =
        this.eventRepositoryCustom.searchEvents(
            name, category, location, dateFrom, dateTo, edition, athleteFirstName, athleteLastName);
    return new EventLookupDTO(events.stream().map(Event::convertToDTO).toList());
  }
}
