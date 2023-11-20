package com.tdd.grupo5.medallero.repositories.impl;

import java.util.Date;
import java.util.List;

import com.tdd.grupo5.medallero.entities.Event;
import com.tdd.grupo5.medallero.repositories.EventRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

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
      String athleteLastName,
      String athleteCountry) {
    StringBuilder sb = new StringBuilder();
    sb.append("SELECT * FROM Event e");
    sb.append(" INNER JOIN Classification c ON c.event_id = e.id");
    sb.append(" INNER JOIN Athlete a ON a.classification_id = c.id");
    sb.append(
        buildSearchConditions(
            name,
            category,
            location,
            dateFrom,
            dateTo,
            edition,
            athleteFirstName,
            athleteLastName,
            athleteCountry));
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
            athleteLastName,
            athleteCountry);
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
      String athleteLastName,
      String athleteCountry) {
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
      sb.append(" a.first_name = :athleteFirstName");
      first = false;
    }
    if (athleteLastName != null) {
      addAndForFirstArgument(sb, first);
      sb.append(" a.last_name = :athleteLastName");
      first = false;
    }
    if (athleteCountry != null) {
      addAndForFirstArgument(sb, first);
      sb.append(" a.country = :athleteCountry");
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
      String athleteLastName,
      String athleteCountry) {
    Query query = entityManager.createNativeQuery(sb.toString(), Event.class);
    query.setParameter("name", name);
    query.setParameter("category", category);
    query.setParameter("location", location);
    query.setParameter("edition", edition);
    query.setParameter("athleteFirstName", athleteFirstName);
    query.setParameter("athleteLastName", athleteLastName);
    query.setParameter("athleteCountry", athleteCountry);
    if (dateFrom != null) {
      query.setParameter("dateFrom", dateFrom.toInstant().toString());
    }
    if (dateTo != null) {
      query.setParameter("dateTo", dateTo.toInstant().toString());
    }
    return query;
  }
}
