package com.tdd.grupo5.medallero.controller.dto;

import com.tdd.grupo5.medallero.entities.Classification;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class ClassificationDTO {
  private int duration;
  private AthleteDTO athlete;
  private int position;
  private boolean pendingValidation;

  public Classification convertToEntity() {
    return new Classification(duration, position, athlete.convertToEntity(), pendingValidation);
  }
}
