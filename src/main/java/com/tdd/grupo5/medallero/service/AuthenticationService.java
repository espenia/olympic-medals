package com.tdd.grupo5.medallero.service;

import com.tdd.grupo5.medallero.controller.dto.JwtAuthenticationResponseDTO;
import com.tdd.grupo5.medallero.controller.dto.UserDTO;
import com.tdd.grupo5.medallero.entities.User;
import com.tdd.grupo5.medallero.exception.BadRequestException;
import com.tdd.grupo5.medallero.exception.NotFoundException;
import com.tdd.grupo5.medallero.util.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.tdd.grupo5.medallero.util.Constants.INVALID_USER;

@Service
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    //private final AuthenticationManager authenticationManager;

    //private final PasswordEncoder passwordEncoder;

    public AuthenticationService(UserService userService, JwtService jwtService) {
                                 //AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder){
        this.userService = userService;
        this.jwtService = jwtService;
        //this.authenticationManager = authenticationManager;
        //this.passwordEncoder = passwordEncoder;
    }
    public JwtAuthenticationResponseDTO signup(UserDTO userDTO) {
        validateUser(userDTO);
        User user = userService.createUser(userDTO);
        String jwt = jwtService.generateToken(userService.userDetailsService(user)
                .loadUserByUsername(user.getUserName()));
        return new JwtAuthenticationResponseDTO(jwt);
    }

    public JwtAuthenticationResponseDTO login(UserDTO userDTO) {
        //authenticationManager.
        //        authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUserName(), userDTO.getPassword()));
        User user = userService.getUser(userDTO);
        String jwt = jwtService.generateToken(userService.userDetailsService(user)
                .loadUserByUsername(user.getUserName()));
        return new JwtAuthenticationResponseDTO(jwt);
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
