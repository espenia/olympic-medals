package com.tdd.grupo5.medallero.controller;

import com.tdd.grupo5.medallero.controller.dto.JwtAuthenticationResponseDTO;
import com.tdd.grupo5.medallero.controller.dto.RecoveryDTO;
import com.tdd.grupo5.medallero.controller.dto.UserDTO;
import com.tdd.grupo5.medallero.controller.dto.AthleteDTO;
import com.tdd.grupo5.medallero.service.AuthenticationService;
import com.tdd.grupo5.medallero.service.UserService;
import com.tdd.grupo5.medallero.service.AthleteService;
import com.tdd.grupo5.medallero.util.smtp.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
  private final AuthenticationService authenticationService;
  private final AthleteService athleteService;
  private final UserService userService;
  private final EmailService emailService;

  public AuthenticationController(
      AuthenticationService authenticationService,
      UserService userService,
      EmailService emailService,
      AthleteService athleteService) {
    this.authenticationService = authenticationService;
    this.userService = userService;
    this.emailService = emailService;
    this.athleteService = athleteService;
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("/signup")
  public ResponseEntity<JwtAuthenticationResponseDTO> signup(@RequestBody UserDTO user) {
    JwtAuthenticationResponseDTO auth = authenticationService.signup(user);
    if (user.getIsAthlete()) {
      athleteService.createAthlete(new AthleteDTO(
        Long.valueOf(0),
        user.getFirstName(),
        user.getLastName(),
        user.getCountry(),
        user.getBirthDate(),
        0,
        0,
        0,
        userService.internalGetUser(user).getId()));
    }
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
  public ResponseEntity<String> recoverPassword(@RequestBody RecoveryDTO recoveryDTO) {
    userService.getUserByMail(recoveryDTO.getMail());
    emailService.sendRestorePasswordLink(
        recoveryDTO.getMail(), recoveryDTO.getRedirectUrl(), "Restaurar Contraseña");
    return new ResponseEntity<>("Mail mandado", HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/password/{mail}")
  public ResponseEntity<JwtAuthenticationResponseDTO> changePassword(
      @PathVariable String mail, @RequestBody UserDTO new_password) {

    JwtAuthenticationResponseDTO response =
        this.authenticationService.updatePasswordFor(mail, new_password);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
