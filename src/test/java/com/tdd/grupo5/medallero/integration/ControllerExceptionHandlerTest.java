package com.tdd.grupo5.medallero.integration;

import com.tdd.grupo5.medallero.controller.PingController;
import com.tdd.grupo5.medallero.exception.BaseAPIException;
import com.tdd.grupo5.medallero.exception.dto.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;

class ControllerExceptionHandlerTest extends ControllerTest {

  @SpyBean
  private PingController pingController;

  protected ControllerExceptionHandlerTest() {
  }

  @Test
  void notFound() {
    // When
    ResponseEntity<ErrorResponse> responseEntity =
        this.testRestTemplate.exchange(
            "/fake", HttpMethod.GET, this.getDefaultRequestEntity(), ErrorResponse.class);

    // Then
    assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
  }

  @Test
  void testUnhandledException() {
    // Given
    doThrow(new RuntimeException()).when(pingController).ping();

    // When
    ResponseEntity<ErrorResponse> responseEntity =
        this.testRestTemplate.exchange(
            "/ping", HttpMethod.GET, this.getDefaultRequestEntity(), ErrorResponse.class);

    // Then
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
  }

  @Test
  void testApiExceptionError() {
    // Given
    doThrow(new BaseAPIException("error",  HttpStatus.INTERNAL_SERVER_ERROR, "error"))
        .when(pingController)
        .ping();

    // When
    ResponseEntity<ErrorResponse> responseEntity =
        this.testRestTemplate.exchange(
            "/ping", HttpMethod.GET, this.getDefaultRequestEntity(), ErrorResponse.class);

    // Then
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
  }

  @Test
  void testApiExceptionWarn() {
    // Given
    doThrow(new BaseAPIException("warn", HttpStatus.BAD_REQUEST, "warn"))
        .when(pingController)
        .ping();

    // When
    ResponseEntity<ErrorResponse> responseEntity =
        this.testRestTemplate.exchange(
            "/ping", HttpMethod.GET, this.getDefaultRequestEntity(), ErrorResponse.class);

    // Then
    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
  }
}
