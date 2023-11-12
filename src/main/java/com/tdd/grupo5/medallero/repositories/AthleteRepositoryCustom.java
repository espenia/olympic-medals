package com.tdd.grupo5.medallero.repositories;

import com.tdd.grupo5.medallero.entities.Athlete;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface AthleteRepositoryCustom {
  List<Athlete> searchAthletes(
      String firstName, String lastName, String country, Date birthDateFrom, Date birthDateTo);
}