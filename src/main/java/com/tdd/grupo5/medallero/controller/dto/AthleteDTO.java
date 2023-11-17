package com.tdd.grupo5.medallero.controller.dto;

import com.tdd.grupo5.medallero.entities.Athlete;
import java.util.Date;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public final class AthleteDTO {
  private String firstName;
  private String lastName;
  private String country;
  private Date birthDate;
  private int goldMedals;
  private int silverMedals;
  private int bronzeMedals;

  public Athlete convertToEntity() {
    return new Athlete(
        firstName, lastName, country, birthDate, goldMedals, silverMedals, bronzeMedals);
  }
}
