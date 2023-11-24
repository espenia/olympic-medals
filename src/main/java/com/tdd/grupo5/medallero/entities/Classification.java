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
  private Integer duration_hours;

  @Basic(optional = false)
  @Column(name = "duration_minutes", nullable = false)
  private Integer duration_minutes;

  @Basic(optional = false)
  @Column(name = "duration_seconds", nullable = false)
  private Integer duration_seconds;

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
      Integer duration_hs,
      Integer duration_ms,
      Integer duration_ss,
      Integer position,
      String athleteFirstName,
      String athleteLastName,
      Athlete athlete) {
    this.duration_hours = duration_hs;
    this.duration_minutes = duration_ms;
    this.duration_seconds = duration_ss;
    this.position = position;
    this.athlete = athlete;
    this.athleteFirstName = athleteFirstName;
    this.athleteLastName = athleteLastName;
  }

  public ClassificationDTO convertToDTO() {
    return ClassificationDTO.builder()
        .athlete(this.athlete == null ? null : this.athlete.convertDTO())
        .duration_hours(this.duration_hours)
        .duration_minutes(this.duration_minutes)
        .duration_seconds(this.duration_seconds)
        .position(this.position)
        .athleteFirstName(this.athleteFirstName)
        .athleteLastName(this.athleteLastName)
        .id(this.id)
        .build();
  }
}
