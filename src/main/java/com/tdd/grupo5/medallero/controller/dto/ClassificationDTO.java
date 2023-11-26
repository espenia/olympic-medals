package com.tdd.grupo5.medallero.controller.dto;

import com.tdd.grupo5.medallero.entities.Classification;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class ClassificationDTO {
  private Long id;
  private Integer durationHours;
  private Integer durationMinutes;
  private Integer durationSeconds;
  @Nullable private AthleteDTO athlete;
  @Nullable private EventDTO event;
  private Integer position;
  private String athleteFirstName;
  private String athleteLastName;

  public Classification convertToEntity() {
    return new Classification(
        durationHours,
        durationMinutes,
        durationSeconds,
        position,
        athleteFirstName,
        athleteLastName,
        athlete == null ? null : athlete.convertToEntity());
  }
}
