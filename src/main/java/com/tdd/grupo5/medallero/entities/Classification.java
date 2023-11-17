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
  private int duration;

  @Property("position")
  private int position;

  @Relationship(type = "CLASSIFIED_WITH", direction = Relationship.Direction.INCOMING)
  private Athlete athlete;

  @Property("pending_validation")
  private boolean pendingValidation;

  public Classification(int duration, int position, Athlete athlete, boolean pending) {
    this.duration = duration;
    this.position = position;
    this.athlete = athlete;
    this.pendingValidation = pending; // siempre que el admin cree una, este campo debe ser "false"
  }

  public ClassificationDTO convertToDTO() {
    return ClassificationDTO.builder()
        .athlete(this.athlete.convertDTO())
        .duration(this.duration)
        .position(this.position)
        .pendingValidation(this.pendingValidation)
        .build();
  }
}
