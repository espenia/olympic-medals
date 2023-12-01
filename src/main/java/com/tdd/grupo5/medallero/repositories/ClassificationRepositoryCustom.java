package com.tdd.grupo5.medallero.repositories;

import com.tdd.grupo5.medallero.entities.Classification;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassificationRepositoryCustom {
  List<Classification> search(
      String eventName, String athleteFirstName, String athleteLastName, Long userId);
  List<Classification> searchByAthleteId(int AthleteId);
}
