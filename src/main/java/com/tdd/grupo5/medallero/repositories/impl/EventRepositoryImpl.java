package com.tdd.grupo5.medallero.repositories.impl;

import com.tdd.grupo5.medallero.entities.Event;
import com.tdd.grupo5.medallero.repositories.EventRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.Date;
import java.util.List;

public class EventRepositoryImpl implements EventRepositoryCustom {

  private final EntityManager entityManager;

  public EventRepositoryImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public List<Event> searchEvents(
      String name,
      String category,
      String location,
      Date dateFrom,
      Date dateTo,
      Integer edition,
      String athleteFirstName,
      String athleteLastName) {
    StringBuilder sb = new StringBuilder();
    sb.append("SELECT DISTINCT e.* FROM Event e");
    sb.append(" INNER JOIN Classification c on c.event_id = e.id");
    sb.append(
        buildSearchConditions(
            name,
            category,
            location,
            dateFrom,
            dateTo,
            edition,
            athleteFirstName,
            athleteLastName));
    Query query =
        buildSearchParameters(
            sb,
            name,
            category,
            location,
            dateFrom,
            dateTo,
            edition,
            athleteFirstName,
            athleteLastName);
    return query.getResultList();
  }

  private StringBuilder buildSearchConditions(
      String name,
      String category,
      String location,
      Date dateFrom,
      Date dateTo,
      Integer edition,
      String athleteFirstName,
      String athleteLastName) {
    StringBuilder sb = new StringBuilder();
    boolean first = true;
    if (name != null) {
      sb.append(" WHERE e.name = :name");
      first = false;
    }
    if (category != null) {
      addAndForFirstArgument(sb, first);
      sb.append(" e.category = :category");
      first = false;
    }
    if (location != null) {
      addAndForFirstArgument(sb, first);
      sb.append(" e.location = :location");
      first = false;
    }
    if (dateFrom != null) {
      addAndForFirstArgument(sb, first);
      sb.append(" e.date >= :dateFrom");
      first = false;
    }
    if (dateTo != null) {
      addAndForFirstArgument(sb, first);
      sb.append(" e.date <= :dateTo");
    }
    if (edition != null) {
      addAndForFirstArgument(sb, first);
      sb.append(" e.edition = :edition");
      first = false;
    }
    if (athleteFirstName != null) {
      addAndForFirstArgument(sb, first);
      sb.append(" c.athlete_first_name = :athleteFirstName");
      first = false;
    }
    if (athleteLastName != null) {
      addAndForFirstArgument(sb, first);
      sb.append(" c.athlete_last_name = :athleteLastName");
      first = false;
    }
    return sb;
  }

  private void addAndForFirstArgument(StringBuilder sb, boolean first) {
    if (!first) {
      sb.append(" AND ");
    } else {
      sb.append(" WHERE ");
    }
  }

  private Query buildSearchParameters(
      StringBuilder sb,
      String name,
      String category,
      String location,
      Date dateFrom,
      Date dateTo,
      Integer edition,
      String athleteFirstName,
      String athleteLastName) {
    Query query = entityManager.createNativeQuery(sb.toString(), Event.class);

    if (name != null) {
      query.setParameter("name", name);
    }
    if (category != null) {
      query.setParameter("category", category);
    }
    if (location != null) {
      query.setParameter("location", location);
    }
    if (edition != null) {
      query.setParameter("edition", edition);
    }
    if (athleteFirstName != null) {
      query.setParameter("athleteFirstName", athleteFirstName);
    }
    if (athleteLastName != null) {
      query.setParameter("athleteLastName", athleteLastName);
    }
    if (dateFrom != null) {
      query.setParameter("dateFrom", dateFrom);
    }
    if (dateTo != null) {
      query.setParameter("dateTo", dateTo);
    }
    return query;
  }
}
