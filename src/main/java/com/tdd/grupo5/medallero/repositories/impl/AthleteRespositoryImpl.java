package com.tdd.grupo5.medallero.repositories.impl;

import com.tdd.grupo5.medallero.entities.Athlete;
import com.tdd.grupo5.medallero.repositories.AthleteRespositoryCustom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.neo4j.driver.Query;
import org.neo4j.driver.internal.summary.InternalResultSummary;

public class AthleteRespositoryImpl implements AthleteRespositoryCustom {

  public List<Athlete> searchAthletes(
      String firstName, String lastName, String country, String birthDate) {
    StringBuilder sb = new StringBuilder();
    sb.append(
        "MATCH (a:Athlete) WHERE a.firstName = $firstName AND a.lastName = $lastName AND a.country"
            + " = $country AND a.birthDate = $birthDate RETURN a ");
    sb.append(buildSearchConditions(firstName, lastName, country));
    Query query = buildSearchParameters(sb, firstName, lastName, country, birthDate);

    return new InternalResultSummary(query);
  }

  private StringBuilder buildSearchConditions(String firstName, String lastName, String country) {
    StringBuilder sb = new StringBuilder();
    if (firstName != null) {
      sb.append(" AND t.firstName = :firstName");
    }
    if (lastName != null) {
      sb.append(" AND t.lastName = :lastName");
    }
    if (country != null) {
      sb.append(" AND t.country = :country");
    }
    return sb;
  }

  private Query buildSearchParameters(
      StringBuilder sb, String firstName, String lastName, String country, String birthDate) {
    Query query = new Query(sb.toString());
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("firstName", firstName);
    parameters.put("lastName", lastName);
    parameters.put("country", country);
    query.withParameters(parameters);
    return query;
  }
}
