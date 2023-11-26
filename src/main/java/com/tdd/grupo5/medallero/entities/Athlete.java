package com.tdd.grupo5.medallero.entities;

import com.tdd.grupo5.medallero.controller.dto.AthleteDTO;
import jakarta.persistence.*;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "athlete")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Athlete {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Basic(optional = false)
  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Basic(optional = false)
  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Basic(optional = false)
  @Column(name = "country", nullable = false)
  private String country;

  @Basic
  @Column(name = "birth_date")
  private Date birthDate;

  @Basic(optional = false)
  @Column(name = "gold_medals", nullable = false)
  private Integer goldMedals;

  @Basic(optional = false)
  @Column(name = "silver_medals", nullable = false)
  private Integer silverMedals;

  @Basic(optional = false)
  @Column(name = "bronze_medals", nullable = false)
  private Integer bronzeMedals;

  @Basic(optional = false)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  @OneToOne(fetch = FetchType.LAZY)
  private User user;

  public Athlete(
      String firstName,
      String lastName,
      String country,
      Date birthDate,
      Integer goldMedals,
      Integer silverMedals,
      Integer bronzeMedals,
      User user) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.country = country;
    this.birthDate = birthDate;
    this.goldMedals = goldMedals;
    this.silverMedals = silverMedals;
    this.bronzeMedals = bronzeMedals;
    this.user = user;
  }

  public AthleteDTO convertDTO() {
    return AthleteDTO.builder()
        .firstName(this.firstName)
        .lastName(this.lastName)
        .country(this.country)
        .birthDate(this.birthDate)
        .bronzeMedals(this.bronzeMedals)
        .goldMedals(this.goldMedals)
        .silverMedals(this.silverMedals)
        .id(this.id)
        .userName(this.user.getUserName())
        .build();
  }
}
