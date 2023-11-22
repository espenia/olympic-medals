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
      Long id,
      String firstName,
      String lastName,
      String country,
      Date birthDateFrom,
      Date birthDateTo) {
    StringBuilder sb = new StringBuilder();
    sb.append(" SELECT a FROM Athlete a");
    sb.append(buildSearchConditions(id, firstName, lastName, country, birthDateFrom, birthDateTo));
    Query query =
        buildSearchParameters(sb, id, firstName, lastName, country, birthDateFrom, birthDateTo);
    return query.getResultList();
  }

  private StringBuilder buildSearchConditions(
      Long id,
      String firstName,
      String lastName,
      String country,
      Date birthDateFrom,
      Date birthDateTo) {
    StringBuilder sb = new StringBuilder();
    boolean first = true;
    if (id != null) {
      sb.append(" WHERE a.id = :id");
      first = false;
    }
    if (firstName != null) {
      addAndForFirstArgument(sb, first);
      sb.append(" a.firstName = :firstName");
      first = false;
    }
    if (lastName != null) {
      addAndForFirstArgument(sb, first);
      sb.append(" a.lastName = :lastName");
      first = false;
    }
    if (country != null) {
      addAndForFirstArgument(sb, first);
      sb.append(" a.country = :country");
      first = false;
    }
    if (birthDateFrom != null) {
      addAndForFirstArgument(sb, first);
      sb.append(" a.birthDate >= :birthDateFrom");
      first = false;
    }
    if (birthDateTo != null) {
      addAndForFirstArgument(sb, first);
      sb.append(" a.birthDate <= :birthDateTo");
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
      Long id,
      String firstName,
      String lastName,
      String country,
      Date birthDateFrom,
      Date birthDateTo) {
    Query query = entityManager.createQuery(sb.toString(), Athlete.class);

    if (id != null) {
      query.setParameter("id", id);
    }
    if (firstName != null) {
      query.setParameter("firstName", firstName);
    }
    if (lastName != null) {
      query.setParameter("lastName", lastName);
    }
    if (country != null) {
      query.setParameter("country", country);
    }
    if (birthDateFrom != null) {
      query.setParameter("birthDateFrom", birthDateFrom);
    }
    if (birthDateTo != null) {
      query.setParameter("birthDateTo", birthDateTo);
    }
    return query;
  }
}
