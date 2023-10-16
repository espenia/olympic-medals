package com.tdd.grupo5.medallero.service.security;

import com.tdd.grupo5.medallero.controller.dto.JwtAuthenticationResponseDTO;
import com.tdd.grupo5.medallero.controller.dto.UserDTO;
import com.tdd.grupo5.medallero.entities.User;
import com.tdd.grupo5.medallero.service.UserService;
import com.tdd.grupo5.medallero.util.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserService userService, JwtService jwtService, AuthenticationManager authenticationManager){
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }
    public JwtAuthenticationResponseDTO signup(UserDTO userDTO) {
        User user = userService.createUser(userDTO);
        String jwt = jwtService.generateToken(userService.userDetailsService(user)
                .loadUserByUsername(user.getUserName()));
        return new JwtAuthenticationResponseDTO(jwt);
    }

    public JwtAuthenticationResponseDTO login(UserDTO userDTO) {
        authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken(userDTO.getMail(), userDTO.getPassword()));
        User user = userService.getUser(userDTO);
        String jwt = jwtService.generateToken(userService.userDetailsService(user)
                .loadUserByUsername(user.getUserName()));
        return new JwtAuthenticationResponseDTO(jwt);
    }
}
