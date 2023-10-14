package com.tdd.grupo5.medallero.exception.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

/**
 * DTO used to respond when there are validation errors (Bean Validation). This object
 * will be returned as a JSON object.
 *
 * @author Paulo Roberto Martins
 * @version 1.0
 * @since 30/11/2017
 */
public class UnprocessableEntity extends ErrorResponse {

    @JsonProperty("cause")
    private final Set<ValidationError> validationErrors = new LinkedHashSet<>();

    public UnprocessableEntity() {
        super(UNPROCESSABLE_ENTITY, "validation_error", "Invalid entity.");
    }

    public UnprocessableEntity(String message) {
        super(UNPROCESSABLE_ENTITY, "validation_error", message);
    }

    public void addValidatonError(String property, String message) {
        this.validationErrors.add(new ValidationError(property, message));
    }

    @JsonIgnore
    public String getValidationErrorsMessage() {
        return validationErrors.stream().map(v -> "[property:" + v.property + "][message:" + v.message + "]").reduce("", (a, b) -> a.concat(b));
    }

    @JsonIgnore
    public String getCause() {
        return super.getCause();
    }

    private static class ValidationError {

        private String property;
        private String message;

        public ValidationError(String property, String message) {
            this.property = property;
            this.message = message;
        }

        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = property;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ValidationError that = (ValidationError) o;

            if (property != null ? !property.equals(that.property) : that.property != null) return false;
            return message != null ? message.equals(that.message) : that.message == null;
        }

        @Override
        public int hashCode() {
            int result = property != null ? property.hashCode() : 0;
            result = 31 * result + (message != null ? message.hashCode() : 0);
            return result;
        }
    }
}

