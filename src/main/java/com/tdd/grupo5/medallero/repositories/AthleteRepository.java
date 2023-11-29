package com.tdd.grupo5.medallero.repositories;

import com.tdd.grupo5.medallero.entities.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AthleteRepository extends JpaRepository<Athlete, Long> {
  @Query("SELECT a FROM Athlete a WHERE a.user.userName = ?1")
  Athlete getAthleteByUserName(String userName);
}
