package com.tdd.grupo5.medallero.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @Basic
    @Column(name = "password", nullable = false)
    private String password;

    @Basic
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
    @Column(name = "mail", nullable = false)
    private String mail;


    private Athlete athlete;

    @Basic
    @Column(name = "role", nullable = false)
    private Role role;

    public User(String userName, String password, String mail, Role role) {
        this.userName = userName;
        this.password = password;
        this.mail = mail;
        this.role = role;
    }
}
