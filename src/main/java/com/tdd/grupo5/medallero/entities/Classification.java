package com.tdd.grupo5.medallero.entities;

import com.tdd.grupo5.medallero.controller.dto.ClassificationDTO;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

@NodeEntity("classification")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Classification {
  @GeneratedValue @Id public UUID id;

  @Property("duration")
  private int duration;

  @Property("position")
  private int position;

  @Relationship(type = "CLASSIFIED_WITH", direction = Relationship.Direction.INCOMING)
  private Athlete athlete;

  public Classification(int duration, int position, Athlete athlete) {
    this.duration = duration;
    this.position = position;
    this.athlete = athlete;
  }

  public ClassificationDTO convertToDTO() {
    return ClassificationDTO.builder()
        .athlete(this.athlete.convertDTO())
        .duration(this.duration)
        .position(this.position)
        .build();
  }
}
