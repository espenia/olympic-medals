package com.tdd.grupo5.medallero.util.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tdd.grupo5.medallero.entities.Athlete;
import com.tdd.grupo5.medallero.util.config.BeanConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.neo4j.driver.Value;
import org.neo4j.driver.Values;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.core.convert.Neo4jPersistentPropertyConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Slf4j
public class ClassificationConverter implements Neo4jPersistentPropertyConverter<Athlete> {

  @Autowired
  private ObjectMapper objectMapper =
      BeanConfiguration.getInstance(new Jackson2ObjectMapperBuilder());

  //    @Override
  //    public Object convert(final Object source, final TypeDescriptor sourceType, final
  // TypeDescriptor targetType) {
  //        if (sourceType.getType().equals(StringValue.class)) {
  //            return this.toEntityAttribute((StringValue) source);
  //        } else {
  //            return this.toGraphProperty((Athlete) source);
  //        }
  //    }
  //
  //    @Override
  //    public Set<ConvertiblePair> getConvertibleTypes() {
  //        Set<ConvertiblePair> convertiblePairs = new HashSet<>();
  //        convertiblePairs.add(new ConvertiblePair(Athlete.class, Value.class));
  //        convertiblePairs.add(new ConvertiblePair(Value.class, Athlete.class));
  //        return convertiblePairs;
  //    }
  //
  //    private Athlete toEntityAttribute(final StringValue value) {
  //        Athlete result;
  //        try {
  //            result = objectMapper.convertValue(value.asString(), Athlete.class);
  //        }
  //        catch (IllegalArgumentException e) {
  //            log.warn(e.getMessage(), e);
  //            throw  e;
  //        }
  //        log.info("reading result:" + result);
  //        return result;
  //    }
  //
  //    private Value toGraphProperty(final Athlete value) {
  //        String result;
  //        try {
  //            result = objectMapper.writeValueAsString(value);
  //        } catch (JsonProcessingException e) {
  //            log.warn(e.getMessage(), e);
  //            throw new RuntimeException(e);
  //        }
  //        return Values.value(result);
  //    }

  @Override
  public Value write(Athlete source) {
    String result;
    try {
      result = objectMapper.writeValueAsString(source);
    } catch (JsonProcessingException e) {
      log.warn(e.getMessage(), e);
      throw new RuntimeException(e);
    }
    return Values.value(result);
  }

  @Override
  public Athlete read(Value source) {
    Athlete result;
    try {
      result = objectMapper.convertValue(source.asString(), Athlete.class);
    } catch (IllegalArgumentException e) {
      log.warn(e.getMessage(), e);
      throw e;
    }
    log.info("reading result:" + result);
    return result;
  }
}
