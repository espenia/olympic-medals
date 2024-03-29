package com.tdd.grupo5.medallero.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tdd.grupo5.medallero.controller.dto.ClassificationDTO;
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

  @JoinColumn(name = "athlete_id", referencedColumnName = "id")
  @OneToOne(fetch = FetchType.EAGER)
  private Athlete athlete;

  @JsonIgnore
  @JoinColumn(name = "event_id", referencedColumnName = "id")
  @ManyToOne(fetch = FetchType.EAGER)
  private Event event;

  public Classification(
      Integer durationHours,
      Integer durationMinutes,
      Integer durationSeconds,
      Integer position,
      String athleteFirstName,
      String athleteLastName,
      Athlete athlete) {
    this.durationHours = durationHours;
    this.durationMinutes = durationMinutes;
    this.durationSeconds = durationSeconds;
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
        .durationMinutes(this.durationMinutes)
        .durationHours(this.durationHours)
        .durationSeconds(this.durationSeconds)
        .position(this.position)
        .athleteFirstName(this.athleteFirstName)
        .athleteLastName(this.athleteLastName)
        .event(this.event == null ? null : this.event.convertToDTOWithoutClassifications())
        .id(this.id)
        .build();
  }

  public ClassificationDTO convertToDTOWithoutEvent() {
    return ClassificationDTO.builder()
        .athlete(this.athlete == null ? null : this.athlete.convertDTO())
        .durationMinutes(this.durationMinutes)
        .durationHours(this.durationHours)
        .durationSeconds(this.durationSeconds)
        .position(this.position)
        .athleteFirstName(this.athleteFirstName)
        .athleteLastName(this.athleteLastName)
        .id(this.id)
        .build();
  }
}
