package com.tdd.grupo5.medallero.entities;

import com.tdd.grupo5.medallero.controller.dto.AthleteDTO;
import java.util.Date;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Property;

@NodeEntity("Athlete")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Athlete {
  @Id @GeneratedValue private UUID id;

  @Property("first_name")
  private String firstName;

  @Property(name = "last_name")
  private String lastName;

  @Property(name = "country")
  private String country;

  @Property(name = "birth_date")
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
