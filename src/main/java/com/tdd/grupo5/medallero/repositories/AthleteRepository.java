package com.tdd.grupo5.medallero.repositories;

import com.tdd.grupo5.medallero.entities.Athlete;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import java.util.UUID;

public interface AthleteRepository extends Neo4jRepository<Athlete, UUID> {
}
