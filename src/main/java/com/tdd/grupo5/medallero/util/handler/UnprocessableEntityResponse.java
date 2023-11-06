package com.tdd.grupo5.medallero.util.handler;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tdd.grupo5.medallero.exception.dto.ErrorResponse;
import java.util.Set;
import org.springframework.http.HttpStatus;

public class UnprocessableEntityResponse extends ErrorResponse {
  private final Set<Validation> validations;
  private final String entityName;

  public UnprocessableEntityResponse(String entityName, Set<Validation> validations) {
    super(
        HttpStatus.UNPROCESSABLE_ENTITY,
        "validation_error",
        String.format("Invalid entity %s.", entityName));
    this.validations = validations;
    this.entityName = entityName;
  }

  @JsonIgnore
  public String getValidationErrorsMessage() {
    return (String)
        this.validations.stream()
            .map(
                (v) -> {
                  return "["
                      + this.entityName
                      + "."
                      + v.getProperty()
                      + ": "
                      + v.getMessage()
                      + "]";
                })
            .reduce("", String::concat);
  }
}
