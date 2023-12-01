package com.tdd.grupo5.medallero.repositories.impl;

import com.tdd.grupo5.medallero.entities.Classification;
import com.tdd.grupo5.medallero.repositories.ClassificationRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;

public class ClassificationRepositoryImpl implements ClassificationRepositoryCustom {

  private final EntityManager entityManager;

  public ClassificationRepositoryImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @SuppressWarnings("unchecked")
  public List<Classification> searchByAthleteId(int athleteId) {
    String stringQuery = "SELECT c FROM Classification c WHERE c.athlete.id = "+ athleteId;
    return entityManager.createQuery(stringQuery).getResultList();
  }

  public List<Classification> search(
      String eventName, String athleteFirstName, String athleteLastName, Long userId) {
    StringBuilder sb = new StringBuilder();
    sb.append(" SELECT c FROM Classification c");
    sb.append(" LEFT JOIN Athlete a ON a.id = c.athlete.id");
    sb.append(buildSearchConditions(eventName, athleteFirstName, athleteLastName, userId));
    Query query = buildSearchParameters(sb, eventName, athleteFirstName, athleteLastName, userId);
    return query.getResultList();
  }

  private StringBuilder buildSearchConditions(
      String eventName, String athleteFirstName, String athleteLastName, Long userId) {
    StringBuilder sb = new StringBuilder();
    boolean first;
    if (userId != null) {
      sb.append(" WHERE a.id = :userId");
    } else {
      sb.append(" WHERE c.athlete.id IS NULL");
    }
    first = false;
    if (eventName != null) {
      addAndForFirstArgument(sb, first);
      sb.append(" c.event.name = :eventName");
    }
    if (athleteFirstName != null) {
      addAndForFirstArgument(sb, first);
      sb.append(" c.athleteFirstName = :athleteFirstName");
    }
    if (athleteLastName != null) {
      addAndForFirstArgument(sb, first);
      sb.append(" c.athleteLastName = :athleteLastName");
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
      String eventName,
      String athleteFirstName,
      String athleteLastName,
      Long userId) {
    Query query = entityManager.createQuery(sb.toString(), Classification.class);

    if (eventName != null) {
      query.setParameter("eventName", eventName);
    }
    if (athleteFirstName != null) {
      query.setParameter("athleteFirstName", athleteFirstName);
    }
    if (athleteLastName != null) {
      query.setParameter("athleteLastName", athleteLastName);
    }
    if (userId != null) {
      query.setParameter("userId", userId);
    }
    return query;
  }
}
