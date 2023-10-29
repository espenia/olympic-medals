package com.tdd.grupo5.medallero.controller.dto;

import java.util.Date;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public final class AthleteDTO {
    private String firstName;
    private String lastName;
    private String country;
    private Date birthDate;
}

