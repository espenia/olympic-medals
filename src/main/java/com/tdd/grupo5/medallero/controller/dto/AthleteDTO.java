package com.tdd.grupo5.medallero.controller.dto;

import com.tdd.grupo5.medallero.entities.Athlete;
import jakarta.validation.constraints.NotBlank;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public final class AthleteDTO {
  private final Long id;
  @NotBlank private String firstName;
  @NotBlank private String lastName;
  @NotBlank private String country;
  private Date birthDate;
  private Integer goldMedals;
  private Integer silverMedals;
  private Integer bronzeMedals;
  private String userName;
  private String userMail;

  public Athlete convertToEntity() {
    return new Athlete(
        firstName, lastName, country, birthDate, goldMedals, silverMedals, bronzeMedals, null);
  }
}
