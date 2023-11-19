package com.tdd.grupo5.medallero.entities;

import com.tdd.grupo5.medallero.controller.dto.AthleteDTO;
import java.util.Date;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@NodeEntity("athlete")
@Node
@Getter
@Setter
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

  @Property(name = "gold_medals")
  private Integer goldMedals;

  @Property(name = "silver_medals")
  private Integer silverMedals;

  @Property(name = "bronze_medals")
  private Integer bronzeMedals;

  public Athlete(
      String firstName,
      String lastName,
      String country,
      Date birthDate,
      Integer goldMedals,
      Integer silverMedals,
      Integer bronzeMedals) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.country = country;
    this.birthDate = birthDate;
    this.goldMedals = goldMedals;
    this.silverMedals = silverMedals;
    this.bronzeMedals = bronzeMedals;
  }

  public AthleteDTO convertDTO() {
    return AthleteDTO.builder()
        .firstName(this.firstName)
        .lastName(this.lastName)
        .country(this.country)
        .birthDate(this.birthDate)
        .bronzeMedals(this.bronzeMedals)
        .goldMedals(this.goldMedals)
        .silverMedals(this.silverMedals)
        .id(this.id)
        .build();
  }
}
