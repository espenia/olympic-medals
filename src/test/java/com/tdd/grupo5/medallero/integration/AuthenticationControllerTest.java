package com.tdd.grupo5.medallero.integration;

import com.tdd.grupo5.medallero.controller.AuthenticationController;
import com.tdd.grupo5.medallero.controller.dto.UserDTO;
import com.tdd.grupo5.medallero.exception.BadRequestException;
import com.tdd.grupo5.medallero.repositories.UserRepository;
import jakarta.annotation.Nullable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AuthenticationControllerTest extends ControllerTest {

    @SpyBean
    private AuthenticationController authenticationController;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        UserDTO dto = new UserDTO("test", "password"
                ,"email@mail.com", "name", "surname", new Date());
        // When
        this.testRestTemplate.exchange(
                "/signup", HttpMethod.POST, this.getDefaultRequestEntity(), UserDTO.class);
        authenticationController.signup(dto);
    }

    @Test
    void signup() {
        // Given
        UserDTO dto = new UserDTO("test_user", "password"
                ,"email@mail.com", "name", "surname", new Date());
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
