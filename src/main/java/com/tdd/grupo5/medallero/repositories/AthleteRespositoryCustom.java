package com.tdd.grupo5.medallero.repositories;

import com.tdd.grupo5.medallero.entities.Athlete;
import java.util.List;

public interface AthleteRespositoryCustom {
  List<Athlete> searchAthletes(String firstName, String lastName, String country, String birthDate);
}
