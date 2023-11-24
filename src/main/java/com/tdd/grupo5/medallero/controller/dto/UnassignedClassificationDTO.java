package com.tdd.grupo5.medallero.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UnassignedClassificationDTO {

  private Long id_classification;
  private Integer position;
  private Integer duration_hours;
  private Integer duration_minutes;
  private Integer duration_seconds;
  String athlete_first_name;
  String athlete_last_name;
  Long event_id;
  String event_name;
  Integer event_edition;
}
