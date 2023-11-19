package com.tdd.grupo5.medallero.controller.dto;

import com.tdd.grupo5.medallero.entities.Classification;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class ClassificationDTO {
  private UUID id;
  private int duration;
  private AthleteDTO athlete;
  private int position;
  private String athleteFirstName;
  private String athleteLastName;
  private boolean pendingValidation;

  public Classification convertToEntity() {
    return new Classification(
        duration,
        position,
        athleteFirstName,
        athleteLastName,
        athlete == null ? null : athlete.convertToEntity(),
        pendingValidation);
  }
}
