package com.tdd.grupo5.medallero.controller.dto;

import java.util.List;

public class EventLookupDTO extends LookupDTO<EventDTO> {
  public EventLookupDTO() {
    super();
  }

  public EventLookupDTO(List<EventDTO> events) {
    super(events);
  }
}
