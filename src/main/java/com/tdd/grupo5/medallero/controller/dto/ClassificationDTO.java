package com.tdd.grupo5.medallero.controller.dto;

import com.tdd.grupo5.medallero.entities.Classification;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class ClassificationDTO {
  private Long id;
  private Integer duration_hours;
  private Integer duration_minutes;
  private Integer duration_seconds;
  @Nullable private AthleteDTO athlete;
  private Integer position;
  private String athleteFirstName;
  private String athleteLastName;

  public Classification convertToEntity() {
    return new Classification(
        duration_hours,
        duration_minutes,
        duration_seconds,
        position,
        athleteFirstName,
        athleteLastName,
        athlete == null ? null : athlete.convertToEntity());
  }
}
