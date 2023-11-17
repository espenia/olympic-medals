package com.tdd.grupo5.medallero.repositories;

import com.tdd.grupo5.medallero.entities.Event;
import java.util.UUID;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends Neo4jRepository<Event, UUID> {}
