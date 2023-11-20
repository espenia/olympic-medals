package com.tdd.grupo5.medallero.controller.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class EventDTO {
  private final Long id;
  @NotBlank private final String name;
  private Integer edition;
  private final Integer participantCount;
  private final String category;
  private Integer distance;
  private final String location;
  private final String description;
  private final Date date;
  private List<ClassificationDTO> classifications;
  private String officialSite;
}
