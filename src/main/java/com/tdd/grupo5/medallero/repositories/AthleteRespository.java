package com.tdd.grupo5.medallero.repositories;

import com.tdd.grupo5.medallero.entities.Athlete;
import java.util.UUID;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface AthleteRespository extends Neo4jRepository<Athlete, UUID> {}
