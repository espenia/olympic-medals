package com.tdd.grupo5.medallero.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class ClassificationDTO {
  private int duration;
  private AthleteDTO athlete;
  private int position;
}
