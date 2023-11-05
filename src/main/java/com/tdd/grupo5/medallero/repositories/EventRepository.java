package com.tdd.grupo5.medallero.repositories;

import com.tdd.grupo5.medallero.entities.Event;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventRepository extends Neo4jRepository<Event, UUID> {
    Event findByName(String eventName);
}
