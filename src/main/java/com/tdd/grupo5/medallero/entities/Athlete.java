package com.tdd.grupo5.medallero.entities;

import com.tdd.grupo5.medallero.controller.dto.AthleteDTO;
import java.util.Date;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
@Getter
@Setter
@NoArgsConstructor
public class Athlete {
  @Id @GeneratedValue private UUID id;

  private String firstName;
  private String lastName;
  private String country;
  private Date birthDate;

  public Athlete(String firstName, String lastName, String country, Date birthDate) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.country = country;
    this.birthDate = birthDate;
  }

  public AthleteDTO convertDTO() {
    return AthleteDTO.builder()
        .firstName(this.firstName)
        .lastName(this.lastName)
        .country(this.country)
        .birthDate(this.birthDate)
        .build();
  }
}
