package com.tdd.grupo5.medallero.repositories;

import com.tdd.grupo5.medallero.entities.Event;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepositoryCustom {
  List<Event> searchEvents(String query);
}
