package com.tdd.grupo5.medallero.controller.dto;

import com.tdd.grupo5.medallero.entities.Classification;
import jakarta.annotation.Nullable;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class ClassificationDTO {
  private UUID id;
  private Integer duration;
  @Nullable private AthleteDTO athlete;
  private Integer position;
  private String athleteFirstName;
  private String athleteLastName;

  public Classification convertToEntity() {
    return new Classification(
        duration,
        position,
        athleteFirstName,
        athleteLastName,
        athlete == null ? null : athlete.convertToEntity());
  }
}
