package com.tdd.grupo5.medallero.repositories.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tdd.grupo5.medallero.entities.Athlete;
import com.tdd.grupo5.medallero.repositories.AthleteRepositoryCustom;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.neo4j.driver.internal.value.NodeValue;
import org.springframework.data.neo4j.core.Neo4jOperations;
import org.springframework.data.neo4j.core.PreparedQuery;

public class AthleteRepositoryImpl implements AthleteRepositoryCustom {

  private final Neo4jOperations template;

  private final ObjectMapper objectMapper;

  public AthleteRepositoryImpl(Neo4jOperations template, ObjectMapper objectMapper) {
    this.template = template;
    this.objectMapper = objectMapper;
  }

  public List<Athlete> searchAthletes(
      String firstName, String lastName, String country, Date birthDateFrom, Date birthDateTo) {
    StringBuilder sb = new StringBuilder();
    sb.append("MATCH (a:Athlete) ");
    sb.append(buildSearchConditions(firstName, lastName, country, birthDateFrom, birthDateTo));
    sb.append(" RETURN a");
    PreparedQuery<NodeValue> query =
        buildSearchParameters(sb, firstName, lastName, country, birthDateFrom, birthDateTo);
    List<NodeValue> results = template.toExecutableQuery(query).getResults();
    List<Athlete> parsedResults = new ArrayList<>();
    results.forEach(
        nodeValue ->
            parsedResults.add(objectMapper.convertValue(nodeValue.asMap(), Athlete.class)));
    return parsedResults;
  }

  private StringBuilder buildSearchConditions(
      String firstName, String lastName, String country, Date birthDateFrom, Date birthDateTo) {
    StringBuilder sb = new StringBuilder();
    boolean first = true;
    if (firstName != null) {
      sb.append(" WHERE a.first_name =~ $firstName");
      first = false;
    }
    if (lastName != null) {
      addAndForFirstArgument(sb, first);
      sb.append("a.last_name =~ $lastName");
      first = false;
    }
    if (country != null) {
      addAndForFirstArgument(sb, first);
      sb.append("a.country =~ $country");
      first = false;
    }
    if (birthDateFrom != null) {
      addAndForFirstArgument(sb, first);
      sb.append("a.birth_date >= $birthDateFrom");
      first = false;
    }
    if (birthDateTo != null) {
      addAndForFirstArgument(sb, first);
      sb.append("a.birth_date <= $birthDateTo");
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

  private PreparedQuery<NodeValue> buildSearchParameters(
      StringBuilder sb,
      String firstName,
      String lastName,
      String country,
      Date birthDateFrom,
      Date birthDateTo) {
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("firstName", firstName);
    parameters.put("lastName", lastName);
    parameters.put("country", country);
    if (birthDateFrom != null) {
      parameters.put("birthDateFrom", birthDateFrom.toInstant().toString());
    }
    if (birthDateTo != null) {
      parameters.put("birthDateTo", birthDateTo.toInstant().toString());
    }

    return PreparedQuery.queryFor(NodeValue.class)
        .withCypherQuery(sb.toString())
        .withParameters(parameters)
        .build();
  }
}
