package com.tdd.grupo5.medallero.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.tdd.grupo5.medallero.controller.dto.UserDTO;
import com.tdd.grupo5.medallero.exception.BadRequestException;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

class AuthenticationControllerTest extends ControllerTest {

  @Test
  void signup() {
    // Given
    UserDTO dto =
        new UserDTO(1L, "test_user", "password", "email@mail.com", "name", "surname", new Date());
    // When
    this.testRestTemplate.exchange(
        "/signup", HttpMethod.POST, this.getDefaultRequestEntity(), UserDTO.class);
    var result = authenticationController.signup(dto);
    // Then
    assertEquals(HttpStatus.CREATED, result.getStatusCode());
    assertNotNull(result.getBody().getToken());
    assertNotNull(userRepository.findByUserName(dto.getUserName()));
  }

  @Test
  void login() {
    // Given
    UserDTO dto = new UserDTO("test", "password");
    // When
    this.testRestTemplate.exchange(
        "/signup", HttpMethod.POST, this.getDefaultRequestEntity(), UserDTO.class);
    var result = authenticationController.login(dto);
    // Then
    assertEquals(HttpStatus.OK, result.getStatusCode());
    assertNotNull(result.getBody().getToken());
  }

  @Test
  void invalid_login() {
    // Given
    UserDTO dto = new UserDTO("test_invalid", "password");
    // When
    this.testRestTemplate.exchange(
        "/signup", HttpMethod.POST, this.getDefaultRequestEntity(), UserDTO.class);
    assertThrows(BadRequestException.class, () -> authenticationController.login(dto));
  }
}
