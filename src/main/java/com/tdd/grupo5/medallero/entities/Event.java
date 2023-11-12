package com.tdd.grupo5.medallero.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
@Getter
@AllArgsConstructor
public class Event {

  @Id @GeneratedValue private UUID eventID;
  private String name;
  private int maxParticipantCount;
  private String eventType;
  private final String placeOfEvent;
  private final String description;
  private boolean isActive;
  private final Date startingDate;
  private final Date endingDate;
  private List<Athlete> listOfParticipants;

  public Event(
      String eventName,
      int maxParticipants,
      String eventType,
      String place,
      String desc,
      Date start,
      Date end) {

    this.name = eventName;
    this.maxParticipantCount = maxParticipants;
    this.eventType = eventType;
    this.placeOfEvent = place;
    this.description = desc;
    this.isActive = true;
    this.startingDate = start;
    this.endingDate = end;
    this.listOfParticipants = new ArrayList<>();
  }

  public void addParticipant(Athlete participant) {

    if (this.listOfParticipants.size() < this.maxParticipantCount) {

      this.listOfParticipants.add(participant);

    } else {
      System.out.printf("[WARN] La cantidad de atletas anotados para este evento ya esta agotada");
    }
  }

  public void changeStatus() {

    if (this.isActive) {

      this.isActive = false;

    } else {

      throw new IllegalStateException("Cannot change the state of an already finished event");
    }
  }
}
