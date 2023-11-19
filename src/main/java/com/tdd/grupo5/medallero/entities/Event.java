package com.tdd.grupo5.medallero.entities;

import com.tdd.grupo5.medallero.controller.dto.EventDTO;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@NodeEntity("event")
@Node
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event {

  @Id @GeneratedValue private UUID id;

  @Property("name")
  private String name;

  @Property("edition")
  private Integer edition;

  @Property("participants_count")
  private Integer participantsCount;

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
  private Integer distance;

  @Property("official_site")
  private String officialSite;

  public Event(
      String eventName,
      Integer edition,
      Integer participantsCount,
      String category,
      String location,
      String desc,
      Date date,
      Integer distance,
      String officialSite,
      List<Classification> classifications) {

    this.name = eventName;
    this.participantsCount = participantsCount;
    this.category = category;
    this.location = location;
    this.description = desc;
    this.date = date;
    this.classifications = classifications;
    this.edition = edition;
    this.distance = distance;
    this.officialSite = officialSite;
  }

  public EventDTO convertToDTO() {

    return EventDTO.builder()
        .name(this.getName())
        .edition(this.getEdition())
        .participantCount(this.getParticipantsCount())
        .category(this.getCategory())
        .location(this.getLocation())
        .description(this.getDescription())
        .distance(this.getDistance())
        .classifications(
            this.getClassifications().stream().map(Classification::convertToDTO).toList())
        .date(this.getDate())
        .id(this.getId())
        .build();
  }
}
