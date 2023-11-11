package com.tdd.grupo5.medallero.unit;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

import com.tdd.grupo5.medallero.controller.dto.EventDTO;
import com.tdd.grupo5.medallero.entities.Event;
import com.tdd.grupo5.medallero.service.EventService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = NONE)
@ActiveProfiles("test")
public class EventServiceTest {

  @Autowired private EventService service;
  private EventDTO dto =
      EventDTO.builder()
          .name("Maraton BSAS")
          .maxParticipantCount(50)
          .eventType("Maraton")
          .placeOfEvent("Buenos Aires")
          .description("Maraton anual")
          .isActive(true)
          .startingDate(new Date())
          .endingDate(new Date())
          .listOfParticipants(new ArrayList<>())
          .build();

  @Test
  void test01CreateEvent() {

    Event event = service.createEvent(dto);
    List<Event> events = service.getEvents();

    assert event.getEventID() != null;
    Assertions.assertEquals(events.get(0).getName(), event.getName());
    Assertions.assertEquals(events.get(0).getMaxParticipantCount(), event.getMaxParticipantCount());
    Assertions.assertEquals(events.get(0).getEventType(), event.getEventType());
    Assertions.assertEquals(events.get(0).getPlaceOfEvent(), event.getPlaceOfEvent());
    Assertions.assertEquals(events.get(0).getDescription(), event.getDescription());
    Assertions.assertEquals(events.get(0).isActive(), event.isActive());
    Assertions.assertEquals(events.get(0).getStartingDate(), event.getStartingDate());
    Assertions.assertEquals(events.get(0).getEndingDate(), event.getEndingDate());
    Assertions.assertEquals(
        events.get(0).getListOfParticipants().isEmpty(), event.getListOfParticipants().isEmpty());
  }

  @Test
  void test02ChangeStateOfEventToInactive() {

    Event event = service.createEvent(dto);
    service.changeEventState(event.getName());

    assert !service.getEvent(event.getName()).isActive();
  }
}
