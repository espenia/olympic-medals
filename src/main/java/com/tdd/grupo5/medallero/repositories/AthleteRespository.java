package com.tdd.grupo5.medallero.repositories;

import com.tdd.grupo5.medallero.entities.Athlete;
import java.util.List;
import java.util.UUID;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

public interface AthleteRespository extends Neo4jRepository<Athlete, UUID> {
  @Query(
      "MATCH (a:Athlete) WHERE a.firstName = $firstName AND a.lastName = $lastName AND a.country ="
          + " $country AND a.birthDate = $birthDate RETURN a")
  List<Athlete> searchAthletes(String firstName, String lastName, String country, String birthDate);
}
