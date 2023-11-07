package com.tdd.grupo5.medallero.entities.converters;

import java.util.List;
import org.neo4j.driver.Value;
import org.springframework.core.convert.converter.Converter;

public abstract class NodeValueConverter<T> implements Converter<Value, List<T>> {
  @Override
  public List<T> convert(Value source) {
    return null;
  }
}
