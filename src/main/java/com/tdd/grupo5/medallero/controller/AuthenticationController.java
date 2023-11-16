package com.tdd.grupo5.medallero.controller;

import com.tdd.grupo5.medallero.controller.dto.JwtAuthenticationResponseDTO;
import com.tdd.grupo5.medallero.controller.dto.UserDTO;
import com.tdd.grupo5.medallero.service.AuthenticationService;
import com.tdd.grupo5.medallero.util.smtp.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
  private final AuthenticationService authenticationService;
  private final EmailService emailService;

  public AuthenticationController(
      AuthenticationService authenticationService, EmailService emailService) {
    this.authenticationService = authenticationService;
    this.emailService = emailService;
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
    JwtAuthenticationResponseDTO authenticationResponseDTO = authenticationService.login(user);
    return new ResponseEntity<>(authenticationResponseDTO, HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.OK)
  @PostMapping("/recovery")
  public ResponseEntity<String> recoverPassword(@RequestBody UserDTO user) {

    emailService.sendRestorePasswordLink(
        user.getMail(),
        "Restaurar Contraseña",
        "Prosiga a cambiar su contraseña usando el siguiente link\n\t*link*");

    return new ResponseEntity<>(user.getUserName(), HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/recovery")
  public ResponseEntity<String> changePassword(@RequestBody UserDTO user) {

    String response = this.authenticationService.updatePasswordFor(user);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
