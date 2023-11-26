package com.tdd.grupo5.medallero.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SearchClassificationDTO {

  private Long classificationId;
  private Integer position;
  private Integer durationHours;
  private Integer durationMinutes;
  private Integer durationSeconds;
  String athleteFirstName;
  String athleteLastName;
  Long eventId;
  String eventName;
  Integer eventEdition;
}
