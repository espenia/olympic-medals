package com.tdd.grupo5.medallero.repositories.impl;


import com.tdd.grupo5.medallero.entities.Athlete;
import com.tdd.grupo5.medallero.repositories.AthleteRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.Date;
import java.util.List;


public class AthleteRepositoryImpl implements AthleteRepositoryCustom {

  private final EntityManager entityManager;

  public AthleteRepositoryImpl(EntityManager entityManager) {
      this.entityManager = entityManager;
  }

  public List<Athlete> searchAthletes(
      String firstName, String lastName, String country, Date birthDateFrom, Date birthDateTo) {
    StringBuilder sb = new StringBuilder();
    sb.append(" SELECT a FROM Athlete a");
    sb.append(buildSearchConditions(firstName, lastName, country, birthDateFrom, birthDateTo));
    Query query =
        buildSearchParameters(sb, firstName, lastName, country, birthDateFrom, birthDateTo);
    return query.getResultList();
  }

  private StringBuilder buildSearchConditions(
      String firstName, String lastName, String country, Date birthDateFrom, Date birthDateTo) {
    StringBuilder sb = new StringBuilder();
    boolean first = true;
    if (firstName != null) {
      sb.append(" WHERE a.first_name = :firstName");
      first = false;
    }
    if (lastName != null) {
      addAndForFirstArgument(sb, first);
      sb.append(" a.last_name = :lastName");
      first = false;
    }
    if (country != null) {
      addAndForFirstArgument(sb, first);
      sb.append(" a.country = :country");
      first = false;
    }
    if (birthDateFrom != null) {
      addAndForFirstArgument(sb, first);
      sb.append(" a.birth_date >= :birthDateFrom");
      first = false;
    }
    if (birthDateTo != null) {
      addAndForFirstArgument(sb, first);
      sb.append(" a.birth_date <= :birthDateTo");
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
      String firstName,
      String lastName,
      String country,
      Date birthDateFrom,
      Date birthDateTo) {
    Query query = entityManager.createQuery(sb.toString(), Athlete.class);

    query.setParameter("firstName", firstName);
    query.setParameter("lastName", lastName);
    query.setParameter("country", country);
    if (birthDateFrom != null) {
      query.setParameter("birthDateFrom", birthDateFrom.toInstant().toString());
    }
    if (birthDateTo != null) {
      query.setParameter("birthDateTo", birthDateTo.toInstant().toString());
    }
    return query;
  }
}
