package com.tdd.grupo5.medallero.repositories.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tdd.grupo5.medallero.entities.Athlete;
import com.tdd.grupo5.medallero.repositories.AthleteRespositoryCustom;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.neo4j.driver.internal.value.NodeValue;
import org.springframework.data.neo4j.core.Neo4jOperations;
import org.springframework.data.neo4j.core.PreparedQuery;

public class AthleteRespositoryImpl implements AthleteRespositoryCustom {

  private final Neo4jOperations template;

  private final ObjectMapper objectMapper;

  public AthleteRespositoryImpl(Neo4jOperations template, ObjectMapper objectMapper) {
    this.template = template;
    this.objectMapper = objectMapper;
  }

  public List<Athlete> searchAthletes(
      String firstName, String lastName, String country, Date birthDateFrom, Date birthDateTo) {
    StringBuilder sb = new StringBuilder();
    sb.append("MATCH (a:Athlete) WHERE ");
    sb.append(buildSearchConditions(firstName, lastName, country, birthDateFrom, birthDateTo));
    sb.append(" RETURN a");
    PreparedQuery<NodeValue> query =
        buildSearchParameters(sb, firstName, lastName, country, birthDateFrom, birthDateTo);
    List<NodeValue> results = template.toExecutableQuery(query).getResults();
    List<Athlete> parsedResults = new ArrayList<>();
    results.forEach(
        nodeValue -> {
          parsedResults.add(objectMapper.convertValue(nodeValue.asMap(), Athlete.class));
        });
    return parsedResults;
  }

  private StringBuilder buildSearchConditions(
      String firstName, String lastName, String country, Date birthDateFrom, Date birthDateTo) {
    StringBuilder sb = new StringBuilder();
    boolean first = true;
    if (firstName != null) {
      sb.append("a.firstName = $firstName");
      first = false;
    }
    if (lastName != null) {
      sb.append(addAndForFirstArgument(sb, first));
      sb.append("a.lastName = $lastName");
      first = false;
    }
    if (country != null) {
      sb.append(addAndForFirstArgument(sb, first));
      sb.append("a.country = $country");
      first = false;
    }
    if (birthDateFrom != null) {
      sb.append(addAndForFirstArgument(sb, first));
      sb.append("a.birthDate >= $birthDateFrom");
      first = false;
    }
    if (birthDateTo != null) {
      sb.append(addAndForFirstArgument(sb, first));
      sb.append("a.birthDate <= $birthDateTo");
    }
    return sb;
  }

  private StringBuilder addAndForFirstArgument(StringBuilder sb, boolean first) {
    if (!first) {
      sb.append(" AND ");
    }
    return sb;
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
    parameters.put("birthDateFrom", birthDateFrom);
    parameters.put("birthDateTo", birthDateTo);
    return PreparedQuery.queryFor(NodeValue.class)
        .withCypherQuery(sb.toString())
        .withParameters(parameters)
        .build();
  }
}
