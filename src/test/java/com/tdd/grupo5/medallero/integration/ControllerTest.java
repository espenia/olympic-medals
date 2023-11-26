package com.tdd.grupo5.medallero.integration;

import com.tdd.grupo5.medallero.controller.AuthenticationController;
import com.tdd.grupo5.medallero.controller.dto.UserDTO;
import com.tdd.grupo5.medallero.repositories.AthleteRepository;
import com.tdd.grupo5.medallero.repositories.UserRepository;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;

public class ControllerTest extends IntegrationTest {
  @Autowired protected TestRestTemplate testRestTemplate;

  @SpyBean protected AuthenticationController authenticationController;

  @Autowired protected UserRepository userRepository;
  @Autowired protected AthleteRepository athleteRepository;

  @BeforeEach
  void setUp() {
    athleteRepository.deleteAll();
    userRepository.deleteAll();
    UserDTO dto =
        new UserDTO(
            1L, "test", "password", "email@mail.com", "name", "surname", new Date(), true, "arg");
    // When
    this.testRestTemplate.exchange(
        "/signup", HttpMethod.POST, this.getDefaultRequestEntity(), UserDTO.class);
    authenticationController.signup(dto);
  }

  protected ControllerTest() {}

  protected <T> RequestEntity<T> getDefaultRequestEntity() {
    HttpHeaders headers = new HttpHeaders();
    return new RequestEntity<>(headers, HttpMethod.GET, null);
  }
}
