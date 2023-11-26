package com.tdd.grupo5.medallero.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {
    @NotBlank
    private String userName;
    //@Pattern(regex=)
    @NotBlank
    private String password;
    @NotBlank
    private String mail;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private Boolean isAthlete = false;
    private String country;

    public UserDTO(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
