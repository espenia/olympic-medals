package com.tdd.grupo5.medallero.repositories;

import com.tdd.grupo5.medallero.entities.Event;

import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepositoryCustom {
  List<Event> searchEvents(String name,
                           String category,
                           String location,
                           Date dateFrom,
                           Date dateTo,
                           Integer edition,
                           String athleteFirstName,
                           String athleteLastName,
                           String athleteCountry);
}
