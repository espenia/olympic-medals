package com.tdd.grupo5.medallero.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tdd.grupo5.medallero.controller.dto.ClassificationDTO;
import com.tdd.grupo5.medallero.controller.dto.UnassignedClassificationDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "classification")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Classification {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  public Long id;

  @Basic(optional = false)
  @Column(name = "duration_hours", nullable = false)
  private Integer durationHours;

  @Basic(optional = false)
  @Column(name = "duration_minutes", nullable = false)
  private Integer durationMinutes;

  @Basic(optional = false)
  @Column(name = "duration_seconds", nullable = false)
  private Integer durationSeconds;

  @Basic(optional = false)
  @Column(name = "position", nullable = false)
  private Integer position;

  @Basic(optional = false)
  @Column(name = "athlete_first_name", nullable = false)
  private String athleteFirstName;

  @Basic(optional = false)
  @Column(name = "athlete_last_name", nullable = false)
  private String athleteLastName;

  @OneToOne(fetch = FetchType.EAGER)
  private Athlete athlete;

  @JsonIgnore
  @JoinColumn(name = "event_id", referencedColumnName = "id")
  @ManyToOne(fetch = FetchType.LAZY)
  private Event event;

  public Classification(
      Integer durationHs,
      Integer durationMs,
      Integer durationSs,
      Integer position,
      String athleteFirstName,
      String athleteLastName,
      Athlete athlete) {
    this.durationHours = durationHs;
    this.durationMinutes = durationMs;
    this.durationSeconds = durationSs;
    this.position = position;
    this.athlete = athlete;
    this.athleteFirstName = athleteFirstName;
    this.athleteLastName = athleteLastName;
  }

  public boolean hasNoAthlete() {

    return this.athlete == null;
  }

  public ClassificationDTO convertToDTO() {
    return ClassificationDTO.builder()
        .athlete(this.athlete == null ? null : this.athlete.convertDTO())
        .durationHours(this.durationHours)
        .durationMinutes(this.durationMinutes)
        .durationSeconds(this.durationSeconds)
        .position(this.position)
        .athleteFirstName(this.athleteFirstName)
        .athleteLastName(this.athleteLastName)
        .id(this.id)
        .build();
  }

  public UnassignedClassificationDTO convertToUnassignedDTO() {

    return UnassignedClassificationDTO.builder()
        .classificationId(this.id)
        .position(this.position)
        .durationHours(this.durationHours)
        .durationMinutes(this.durationMinutes)
        .durationSeconds(this.durationSeconds)
        .athleteFirstName(this.athleteFirstName)
        .athleteLastName(this.athleteLastName)
        .eventId(this.event.getId())
        .eventName(this.event.getName())
        .eventEdition(this.event.getEdition())
        .build();
  }
}
