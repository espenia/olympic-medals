package com.tdd.grupo5.medallero.entities;

import com.tdd.grupo5.medallero.controller.dto.EventDTO;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "event")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Basic(optional = false)
  @Column(name = "name", nullable = false)
  private String name;

  @Basic(optional = false)
  @Column(name = "edition", nullable = false)
  private Integer edition;

  @Basic(optional = false)
  @Column(name = "participants_count")
  private Integer participantsCount;

  @Basic(optional = false)
  @Column(name = "category")
  private String category;

  @Basic(optional = false)
  @Column(name = "location")
  private String location;

  @Basic(optional = false)
  @Column(name = "description")
  private String description;

  @Basic(optional = false)
  @Column(name = "date", nullable = false)
  private Date date;

  @OneToMany(mappedBy = "event", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
  private List<Classification> classifications;

  @Basic(optional = false)
  @Column(name = "distance")
  private Integer distance;

  @Basic(optional = false)
  @Column(name = "official_site")
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
      String officialSite) {

    this.name = eventName;
    this.participantsCount = participantsCount;
    this.category = category;
    this.location = location;
    this.description = desc;
    this.date = date;
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
            this.getClassifications() == null
                ? null
                : this.getClassifications().stream().map(Classification::convertToDTO).toList())
        .date(this.getDate())
        .id(this.getId())
        .build();
  }
}
