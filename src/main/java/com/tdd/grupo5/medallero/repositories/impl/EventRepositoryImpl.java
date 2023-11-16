package com.tdd.grupo5.medallero.repositories.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tdd.grupo5.medallero.entities.Event;
import com.tdd.grupo5.medallero.repositories.EventRepositoryCustom;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.neo4j.driver.internal.value.NodeValue;
import org.springframework.data.neo4j.core.Neo4jOperations;
import org.springframework.data.neo4j.core.PreparedQuery;
import org.springframework.util.StringUtils;

public class EventRepositoryImpl implements EventRepositoryCustom {

  private final Neo4jOperations template;

  private final ObjectMapper objectMapper;

  public EventRepositoryImpl(Neo4jOperations template, ObjectMapper objectMapper) {
    this.template = template;
    this.objectMapper = objectMapper;
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
    sb.append("MATCH (e:Event) ");
    sb.append("-[:HAS_CLASSIFICATION]->(c:Classification) ");
    if (athleteFirstName != null || athleteLastName != null || athleteCountry != null) {
      sb.append("<-[:CLASSIFIED_WITH]-(a:Athlete)");
    }
    sb.append(" WHERE ");
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
    sb.append(" RETURN e");
    PreparedQuery<NodeValue> query =
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
    List<NodeValue> results = template.toExecutableQuery(query).getResults();
    List<Event> parsedResults = new ArrayList<>();
    results.forEach(
        nodeValue -> parsedResults.add(objectMapper.convertValue(nodeValue.asMap(), Event.class)));
    return parsedResults;
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
      sb.append("e.name = $name");
      first = false;
    }
    if (category != null) {
      addAndForFirstArgument(sb, first);
      sb.append("e.category = $category");
      first = false;
    }
    if (location != null) {
      addAndForFirstArgument(sb, first);
      sb.append("e.location = $location");
      first = false;
    }
    if (dateFrom != null) {
      addAndForFirstArgument(sb, first);
      sb.append("e.date >= $dateFrom");
      first = false;
    }
    if (dateTo != null) {
      addAndForFirstArgument(sb, first);
      sb.append("e.date <= $dateTo");
    }
    if (edition != null) {
      addAndForFirstArgument(sb, first);
      sb.append("e.edition = $edition");
      first = false;
    }
    if (athleteFirstName != null) {
      addAndForFirstArgument(sb, first);
      sb.append("a.athlete_last_name = $athleteFirstName");
      first = false;
    }
    if (athleteLastName != null) {
      addAndForFirstArgument(sb, first);
      sb.append("a.athlete_last_name = $athleteLastName");
      first = false;
    }
    if (athleteCountry != null) {
      addAndForFirstArgument(sb, first);
      sb.append("a.athlete_country = $athleteCountry");
      first = false;
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
      String name,
      String category,
      String location,
      Date dateFrom,
      Date dateTo,
      int edition,
      String athleteFirstName,
      String athleteLastName,
      String athleteCountry) {
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("name", name);
    parameters.put("category", category);
    parameters.put("location", location);
    parameters.put("edition", edition);
    parameters.put("athleteFirstName", athleteFirstName);
    parameters.put("athleteLastName", athleteLastName);
    parameters.put("athleteCountry", athleteCountry);
    parameters.put("dateFrom", StringUtils.split(dateFrom.toInstant().toString(), ".")[0]);
    parameters.put("dateTo", StringUtils.split(dateTo.toInstant().toString(), ".")[0]);
    return PreparedQuery.queryFor(NodeValue.class)
        .withCypherQuery(sb.toString())
        .withParameters(parameters)
        .build();
  }
}
