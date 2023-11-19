package com.tdd.grupo5.medallero.repositories.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tdd.grupo5.medallero.entities.Athlete;
import com.tdd.grupo5.medallero.entities.Classification;
import com.tdd.grupo5.medallero.entities.Event;
import com.tdd.grupo5.medallero.repositories.EventRepositoryCustom;
import java.util.ArrayList;
import java.util.List;
import org.neo4j.driver.internal.value.NullValue;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Repository;

@Repository
public class EventRepositoryCustomImpl implements EventRepositoryCustom {

  private final Neo4jClient neo4jClient;
  private final ObjectMapper objectMapper;

  public EventRepositoryCustomImpl(Neo4jClient neo4jClient, ObjectMapper objectMapper) {
    this.neo4jClient = neo4jClient;
    this.objectMapper = objectMapper;
  }

  @Override
  public List<Event> searchEvents(String query) {
    List<Event> result =
        neo4jClient
            .query(query)
            .fetchAs(Event.class)
            .mappedBy(
                (typeSystem, record) -> {
                  Event event = objectMapper.convertValue(record.get("e").asMap(), Event.class);
                  if (record.get("c") == null
                      || record.get("c").equals(NullValue.NULL)
                      || record.get("c").asMap() == null) {
                    return event;
                  }
                  Classification classification =
                      objectMapper.convertValue(record.get("c").asMap(), Classification.class);
                  if (record.get("a") == null
                      || record.get("a").equals(NullValue.NULL)
                      || record.get("a").asMap() == null) {
                    event.setClassifications(
                        new ArrayList<>() {
                          {
                            add(classification);
                          }
                        });
                    return event;
                  }
                  Athlete athlete =
                      objectMapper.convertValue(record.get("a").asMap(), Athlete.class);
                  classification.setAthlete(athlete);
                  event.setClassifications(
                      new ArrayList<>() {
                        {
                          add(classification);
                        }
                      });
                  return event;
                })
            .all()
            .stream()
            .toList();
    List<Event> events = new ArrayList<>();
    for (Event event : result) {
      if (events.stream().anyMatch(e -> e.getId().equals(event.getId()))) {
        Event e = events.stream().filter(ev -> ev.getId().equals(event.getId())).findFirst().get();
        e.getClassifications().addAll(event.getClassifications());
      } else {
        events.add(event);
      }
    }
    return events;
  }
}
