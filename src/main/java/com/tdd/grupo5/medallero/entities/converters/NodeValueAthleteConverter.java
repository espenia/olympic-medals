package com.tdd.grupo5.medallero.entities.converters;

import com.tdd.grupo5.medallero.entities.Athlete;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.neo4j.driver.Value;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;

@Component
@ReadingConverter
public class NodeValueAthleteConverter extends NodeValueConverter<Athlete> {
  @Override
  public List<Athlete> convert(Value source) {
    List<Athlete> athletes = new ArrayList<>();
    source
        .values()
        .forEach(
            value -> {
              athletes.add(
                  new Athlete(
                      UUID.fromString(value.get("id").asString()),
                      value.get("firstName").asString(),
                      value.get("lastName").asString(),
                      value.get("country").asString(),
                      Date.from(Instant.from(value.get("birthDate").asLocalDate()))));
            });
    return athletes;
  }
}
