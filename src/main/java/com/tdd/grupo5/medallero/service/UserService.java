package com.tdd.grupo5.medallero.service;

import static com.tdd.grupo5.medallero.util.Constants.INVALID_COMBINATION_USER_PASSWORD_ERROR;
import static com.tdd.grupo5.medallero.util.Constants.INVALID_COMBINATION_USER_PASSWORD_MSG;
import static com.tdd.grupo5.medallero.util.Constants.INVALID_USER;
import static com.tdd.grupo5.medallero.util.Constants.USER_NOT_FOUND_ERROR;

import com.tdd.grupo5.medallero.controller.dto.AthleteDTO;
import com.tdd.grupo5.medallero.controller.dto.UserDTO;
import com.tdd.grupo5.medallero.entities.Role;
import com.tdd.grupo5.medallero.entities.User;
import com.tdd.grupo5.medallero.exception.BadRequestException;
import com.tdd.grupo5.medallero.exception.NotFoundException;
import com.tdd.grupo5.medallero.repositories.UserRepository;
import com.tdd.grupo5.medallero.util.JwtService;
import java.util.Collections;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final AthleteService athleteService;
  private final JwtService jwtService;

  public UserService(
      UserRepository userRepository,
      PasswordEncoder passwordEncoder,
      AthleteService athleteService,
      JwtService jwtService) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.athleteService = athleteService;
    this.jwtService = jwtService;
  }

  public UserDetailsService userDetailsService() {
    return username -> {
      User user = userRepository.findByUserName(username);
      if (user == null) {
        throw new BadRequestException(
            INVALID_COMBINATION_USER_PASSWORD_ERROR, INVALID_COMBINATION_USER_PASSWORD_MSG);
      }
      return new org.springframework.security.core.userdetails.User(
          user.getUserName(), user.getPassword(), Collections.singleton(user.getRole()));
    };
  }

  public UserDetailsService userDetailsService(final com.tdd.grupo5.medallero.entities.User user) {
    return username ->
        new org.springframework.security.core.userdetails.User(
            user.getUserName(), user.getPassword(), Collections.singleton(user.getRole()));
  }

  public User createUser(UserDTO userDTO) {
    User user =
        new User(
            userDTO.getUserName(),
            passwordEncoder.encode(userDTO.getPassword()),
            userDTO.getFirstName(),
            userDTO.getLastName(),
            userDTO.getBirthDate(),
            userDTO.getMail(),
            Role.Athlete);
    user = userRepository.saveAndFlush(user);
    if (user.getRole().equals(Role.Athlete)) {
      athleteService.createAthlete(
          new AthleteDTO(
              0L,
              userDTO.getFirstName(),
              userDTO.getLastName(),
              userDTO.getCountry(),
              userDTO.getBirthDate(),
              0,
              0,
              0,
              userDTO.getUserName(),
              userDTO.getMail()));
    }
    return user;
  }

  public User getUser(UserDTO userDTO) {
    User user = userRepository.findByUserName(userDTO.getUserName());
    if (user == null || !passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
      throw new BadRequestException(
          INVALID_COMBINATION_USER_PASSWORD_ERROR, INVALID_COMBINATION_USER_PASSWORD_MSG);
    }
    return user;
  }

  public User getUserByMail(String mail) {
    User user = userRepository.findByMail(mail);
    if (user == null) {
      throw new BadRequestException(INVALID_USER, INVALID_USER);
    }
    return user;
  }

  public User updateUser(String mail, UserDTO user) {

    User targetUser = userRepository.findByMail(mail);
    if (targetUser == null || !targetUser.getUserName().equals(user.getUserName())) {
      throw new BadRequestException(INVALID_USER, INVALID_USER);
    }
    targetUser.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.save(targetUser);

    return targetUser;
  }

  public User internalGetUser(UserDTO userDTO) {
    User user = userRepository.findByUserName(userDTO.getUserName());
    if (user == null) {
      throw new NotFoundException(USER_NOT_FOUND_ERROR);
    }
    return user;
  }

  public User getUserByToken(String token) {
    if (!StringUtils.hasLength(token) || !StringUtils.startsWithIgnoreCase(token, "Bearer ")) {
      throw new NotFoundException(USER_NOT_FOUND_ERROR);
    }
    final String jwt = token.substring(7);
    final String userName = jwtService.extractUserName(jwt);
    User user = userRepository.findByUserName(userName);
    if (user == null) {
      throw new NotFoundException(USER_NOT_FOUND_ERROR);
    }
    return user;
  }
}
