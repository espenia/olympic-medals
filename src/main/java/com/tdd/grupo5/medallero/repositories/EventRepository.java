package com.tdd.grupo5.medallero.repositories;

import com.tdd.grupo5.medallero.entities.Event;
import java.util.UUID;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends Neo4jRepository<Event, UUID> {
  //    List<Event> findByNameAndCategoryAndLocationAndEditionAndDateIsAfterAndDateIsBefore(
  //            String name,
  //            String category,
  //            String location,
  //            Integer edition,
  //            Date dateFrom,
  //            Date dateTo);
  //
  //    List<Event> findByNameAndCategoryAndLocationAndEditionAndDateIsAfter(
  //            String name,
  //            String category,
  //            String location,
  //            Integer edition,
  //            Date dateFrom);
  //
  //    List<Event> findByNameAndCategoryAndLocationAndEdition(
  //            String name,
  //            String category,
  //            String location,
  //            Integer edition);
  //
  //    List<Event> findByNameAndCategoryAndLocation(
  //            String name,
  //            String category,
  //            String location);
  //
  //    List<Event> findByNameAndCategory(
  //            String name,
  //            String category);
  //
  //    List<Event> findByName(
  //            String name);

}
