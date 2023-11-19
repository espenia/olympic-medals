package com.tdd.grupo5.medallero.repositories.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.neo4j.driver.Query;
import org.springframework.util.StringUtils;

public class EventRepositoryImpl {

  public static String searchEvents(
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
    sb.append("-[:CLASSIFICATIONS]->(c:Classification) <-[:CLASSIFIED_WITH]-(a:Athlete)");
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
    sb.append(" RETURN e,c,a");
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
    String resultingQuery = query.text();
    for (String entry : query.parameters().asMap().keySet()) {
      if (query.parameters().asMap().get(entry) == null) {
        continue;
      }
      resultingQuery =
          resultingQuery.replace(
              "$" + entry, "'" + query.parameters().asMap().get(entry).toString() + "'");
    }
    return resultingQuery;
  }

  private static StringBuilder buildSearchConditions(
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
      sb.append(" WHERE e.name = $name");
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
      sb.append("a.first_name = $athleteFirstName");
      first = false;
    }
    if (athleteLastName != null) {
      addAndForFirstArgument(sb, first);
      sb.append("a.last_name = $athleteLastName");
      first = false;
    }
    if (athleteCountry != null) {
      addAndForFirstArgument(sb, first);
      sb.append("a.country = $athleteCountry");
      first = false;
    }
    return sb;
  }

  private static void addAndForFirstArgument(StringBuilder sb, boolean first) {
    if (!first) {
      sb.append(" AND ");
    } else {
      sb.append(" WHERE ");
    }
  }

  private static Query buildSearchParameters(
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
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("name", name);
    parameters.put("category", category);
    parameters.put("location", location);
    parameters.put("edition", edition);
    parameters.put("athleteFirstName", athleteFirstName);
    parameters.put("athleteLastName", athleteLastName);
    parameters.put("athleteCountry", athleteCountry);
    if (dateFrom != null) {
      parameters.put("dateFrom", StringUtils.split(dateFrom.toInstant().toString(), ".")[0]);
    }
    if (dateTo != null) {
      parameters.put("dateTo", StringUtils.split(dateTo.toInstant().toString(), ".")[0]);
    }
    return new Query(sb.toString(), parameters);
  }
}
