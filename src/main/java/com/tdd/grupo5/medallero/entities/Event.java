package com.tdd.grupo5.medallero.entities;

import com.tdd.grupo5.medallero.controller.dto.EventDTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Property;

@NodeEntity("event")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Event {

  @Id @GeneratedValue private UUID id;

  @Property("name")
  private String name;

  @Property("edition")
  private int edition;

  @Property("participants_count")
  private int participantsCount;

  @Property("category")
  private String category;

  @Property("location")
  private String location;

  @Property("description")
  private String description;

  @Property("date")
  private Date date;

  @Relationship(type = "HAS_CLASSIFICATION", direction = Relationship.Direction.OUTGOING)
  private List<Classification> classifications;

  @Property("distance")
  private int distance;

  @Property("official_site")
  private String officialSite;

  public Event(
      String eventName,
      int participantsCount,
      String category,
      String location,
      String desc,
      Date date,
      int edition,
      String officialSite) {

    this.name = eventName;
    this.participantsCount = participantsCount;
    this.category = category;
    this.location = location;
    this.description = desc;
    this.date = date;
    this.classifications = new ArrayList<>();
    this.edition = edition;
    this.officialSite = officialSite;
  }

  public EventDTO convertToDTO() {

    return EventDTO.builder()
        .name(this.getName())
        .participantCount(this.getParticipantsCount())
        .category(this.getCategory())
        .location(this.getLocation())
        .description(this.getDescription())
        .date(this.getDate())
        .build();
  }
}
