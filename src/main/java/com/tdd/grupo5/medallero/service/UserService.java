package com.tdd.grupo5.medallero.service;

import com.tdd.grupo5.medallero.controller.dto.UserDTO;
import com.tdd.grupo5.medallero.entities.Role;
import com.tdd.grupo5.medallero.entities.User;
import com.tdd.grupo5.medallero.exception.BadRequestException;
import com.tdd.grupo5.medallero.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

import static com.tdd.grupo5.medallero.util.Constants.INVALID_COMBINATION_USER_PASSWORD_ERROR;
import static com.tdd.grupo5.medallero.util.Constants.INVALID_COMBINATION_USER_PASSWORD_MSG;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDetailsService userDetailsService() {
        return username -> {
            User user = userRepository.findByUserName(username);
            if (user == null) {
                throw new BadRequestException(INVALID_COMBINATION_USER_PASSWORD_ERROR, INVALID_COMBINATION_USER_PASSWORD_MSG);
            }
            return new org.springframework.security.core.userdetails.
                    User(user.getUserName(), user.getPassword(), Collections.singleton(user.getRole()));
        };
    }

    public UserDetailsService userDetailsService(final com.tdd.grupo5.medallero.entities.User user) {
        return username -> new org.springframework.security.core.userdetails.
                User(user.getUserName(), user.getPassword(), Collections.singleton(user.getRole()));
    }


    public User createUser(UserDTO userDTO) {
        User user = new com.tdd.grupo5.medallero.entities.User(userDTO.getUserName(), passwordEncoder.encode(userDTO.getPassword()),
                userDTO.getFirstName(), userDTO.getLastName(), userDTO.getBirthDate(),
                userDTO.getMail(), Role.Athlete);
        userRepository.save(user);
        return user;
    }

    public User getUser(UserDTO userDTO) {
        User user = userRepository.findByUserName(userDTO.getUserName());
        if (user == null || !passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
            throw new BadRequestException(INVALID_COMBINATION_USER_PASSWORD_ERROR, INVALID_COMBINATION_USER_PASSWORD_MSG);
        }
        return user;
    }
}
