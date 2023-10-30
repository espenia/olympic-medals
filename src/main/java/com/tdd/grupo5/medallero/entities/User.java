package com.tdd.grupo5.medallero.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "username")
@Getter
@Setter
@NoArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Basic(optional = false)
  @Column(name = "user_name", nullable = false, unique = true)
  private String userName;

  @Basic(optional = false)
  @Column(name = "password", nullable = false)
  private String password;

  @Basic(optional = false)
  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Basic(optional = false)
  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Basic(optional = false)
  @Column(name = "birth_date", nullable = false)
  private Date birthDate;

  @Basic
  @Column(name = "birth_date", nullable = false)
  private Date birthDate;

  // private Athlete athlete;

  @Basic(optional = false)
  @Column(name = "role", nullable = false)
  private Role role;

  public User(
      String userName,
      String password,
      String firstName,
      String lastName,
      Date birthDate,
      String mail,
      Role athlete) {
    this.userName = userName;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthDate = birthDate;
    this.mail = mail;
    this.role = athlete;
  }
}
