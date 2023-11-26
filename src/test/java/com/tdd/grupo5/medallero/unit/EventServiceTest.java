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
  private final EventDTO dto =
      EventDTO.builder()
          .name("Maraton BSAS")
          .participantCount(50)
          .category("Maraton")
          .location("Buenos Aires")
          .description("Maraton anual")
          .edition(2023)
          .date(new Date())
          .distance(10000)
          .officialSite("www.maratonbsas.com")
          .classifications(new ArrayList<>())
          .build();

  @Test
  void test01CreateEvent() {

    Event event = service.createEvent(dto);
    List<Event> events = service.getEvents();

    assert event.getId() != null;
    Assertions.assertEquals(events.get(0).getName(), event.getName());
    Assertions.assertEquals(events.get(0).getParticipantsCount(), event.getParticipantsCount());
    Assertions.assertEquals(events.get(0).getCategory(), event.getCategory());
    Assertions.assertEquals(events.get(0).getLocation(), event.getLocation());
    Assertions.assertEquals(events.get(0).getDescription(), event.getDescription());
    Assertions.assertEquals(
        events.get(0).getDate().toInstant().toString(), event.getDate().toInstant().toString());
  }
}
