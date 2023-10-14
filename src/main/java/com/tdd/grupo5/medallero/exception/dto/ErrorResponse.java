package com.tdd.grupo5.medallero.exception.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * Base DTO used to respond when an exception happen.
 *
 * @author Paulo Roberto Martins
 * @version 1.0
 * @since 30/11/2017
 */
public class ErrorResponse implements Serializable {

    private HttpStatus status;
    private String message;
    private String error;
    private Throwable cause;

    public ErrorResponse() {
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        this.message = "Internal error. Something did not work well.";
        this.error = HttpStatus.INTERNAL_SERVER_ERROR.name().toLowerCase();
        this.cause = null;
    }

    public ErrorResponse(HttpStatus status, String error) {
        this.status = status;
        this.error = error;
    }

    public ErrorResponse(HttpStatus status, String error, String message) {
        this(status, error);
        this.message = message;
    }

    public ErrorResponse(HttpStatus status, String error, String message, Throwable cause) {
        this(status, error, message);
        this.cause = cause;
    }

    public ErrorResponse(Throwable cause) {
        this();
        this.cause = cause;
    }

    public int getStatus() {
        return status.value();
    }

    @JsonIgnore
    public HttpStatus getStatusObj() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getError() {
        return error;
    }

    public String getCause() {
        if (cause != null) {
            return cause.getLocalizedMessage();
        } else {
            return null;
        }
    }
}