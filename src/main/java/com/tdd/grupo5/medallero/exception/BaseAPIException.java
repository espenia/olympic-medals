package com.tdd.grupo5.medallero.exception;

import org.springframework.http.HttpStatus;

public class BaseAPIException extends RuntimeException {

  private HttpStatus status;
  private String error;

  public BaseAPIException(String message) {
    super(message);
    this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    this.error = HttpStatus.INTERNAL_SERVER_ERROR.name().toLowerCase();
  }

  public BaseAPIException(String message, Throwable cause) {
    super(message, cause);
    this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    this.error = HttpStatus.INTERNAL_SERVER_ERROR.name().toLowerCase();
  }

  public BaseAPIException(String message, Throwable cause, HttpStatus status) {
    super(message, cause);
    this.status = status;
    this.error = status.name().toLowerCase();
  }

  public BaseAPIException(String message, Throwable cause, HttpStatus status, String error) {
    this(message, cause, status);
    this.error = error;
  }

  public BaseAPIException(String message, HttpStatus status) {
    super(message);
    this.status = status;
    this.error = status.name().toLowerCase();
  }

  public BaseAPIException(String message, HttpStatus status, String error) {
    this(message, status);
    this.error = error;
  }

  public HttpStatus getStatus() {
    return this.status;
  }

  public int getStatusCode() {
    return this.status.value();
  }

  public String getError() {
    return this.error;
  }
}
