package com.tdd.grupo5.medallero.service;

import static com.tdd.grupo5.medallero.util.Constants.INVALID_USER;

import com.tdd.grupo5.medallero.controller.dto.JwtAuthenticationResponseDTO;
import com.tdd.grupo5.medallero.controller.dto.UserDTO;
import com.tdd.grupo5.medallero.entities.User;
import com.tdd.grupo5.medallero.exception.BadRequestException;
import com.tdd.grupo5.medallero.exception.NotFoundException;
import com.tdd.grupo5.medallero.util.JwtService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
  private final UserService userService;
  private final JwtService jwtService;

  public AuthenticationService(UserService userService, JwtService jwtService) {
    this.userService = userService;
    this.jwtService = jwtService;
  }

  public JwtAuthenticationResponseDTO signup(UserDTO userDTO) {
    validateUser(userDTO);
    User user = userService.createUser(userDTO);
    String jwt =
        jwtService.generateToken(
            userService.userDetailsService(user).loadUserByUsername(user.getUserName()));
    return new JwtAuthenticationResponseDTO(jwt);
  }

  public JwtAuthenticationResponseDTO login(UserDTO userDTO) {
    User user = userService.getUser(userDTO);
    String jwt =
        jwtService.generateToken(
            userService.userDetailsService(user).loadUserByUsername(user.getUserName()));
    return new JwtAuthenticationResponseDTO(jwt);
  }

  public String updatePasswordFor(UserDTO user) {

    return userService.updateUser(user);
  }

  private void validateUser(UserDTO userDTO) {
    try {
      userService.internalGetUser(userDTO);
      throw new BadRequestException(INVALID_USER);
    } catch (NotFoundException e) {
      // OK
    }
  }
}
