package com.tdd.grupo5.medallero.controller;

import com.tdd.grupo5.medallero.controller.dto.JwtAuthenticationResponseDTO;
import com.tdd.grupo5.medallero.controller.dto.UserDTO;
import com.tdd.grupo5.medallero.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponseDTO> signup(@RequestBody UserDTO user) {
        JwtAuthenticationResponseDTO auth = authenticationService.signup(user);
        return new ResponseEntity<>(auth, HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponseDTO> login(@RequestBody UserDTO user) {
        JwtAuthenticationResponseDTO authenticationResponseDTO =  authenticationService.login(user);
        return new ResponseEntity<>(authenticationResponseDTO, HttpStatus.OK);
    }
}
