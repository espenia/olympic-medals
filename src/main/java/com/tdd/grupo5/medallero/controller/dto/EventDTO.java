package com.tdd.grupo5.medallero.controller.dto;

import java.util.Date;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class EventDTO {
  private final String name;
  private int edition;
  private final int participantCount;
  private final String category;
  private int distance;
  private final String location;
  private final String description;
  private final Date date;
  private List<ClassificationDTO> classifications;
  private String officialSite;
}
