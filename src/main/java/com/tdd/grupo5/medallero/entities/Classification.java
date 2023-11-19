package com.tdd.grupo5.medallero.entities;

import com.tdd.grupo5.medallero.controller.dto.ClassificationDTO;
import com.tdd.grupo5.medallero.util.converters.AthleteConverter;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.data.neo4j.core.convert.ConvertWith;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

@NodeEntity("classification")
@Node
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Classification {
  @GeneratedValue @Id public UUID id;

  @Property("duration")
  private Integer duration;

  @Property("position")
  private Integer position;

  @Property("athlete_first_name")
  private String athleteFirstName;

  @Property("athlete_last_name")
  private String athleteLastName;

  @ConvertWith(converter = AthleteConverter.class)
  @Relationship(type = "CLASSIFIED_WITH", direction = Relationship.Direction.INCOMING)
  private Athlete athlete;

  public Classification(
      Integer duration,
      Integer position,
      String athleteFirstName,
      String athleteLastName,
      Athlete athlete) {
    this.duration = duration;
    this.position = position;
    this.athlete = athlete;
    this.athleteFirstName = athleteFirstName;
    this.athleteLastName = athleteLastName;
  }

  public ClassificationDTO convertToDTO() {
    return ClassificationDTO.builder()
        .athlete(this.athlete == null ? null : this.athlete.convertDTO())
        .duration(this.duration)
        .position(this.position)
        .athleteFirstName(this.athleteFirstName)
        .athleteLastName(this.athleteLastName)
        .id(this.id)
        .build();
  }
}
