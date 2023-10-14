package com.tdd.grupo5.medallero.service;

import com.tdd.grupo5.medallero.entities.User;
import com.tdd.grupo5.medallero.repositories.UserRepository;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public OAuth2AccessToken login(String userName, String password) {
        User user = userRepository.findByUserName(userName);
        if (user != null && user.getPassword().equals(password)) {
            return Bad;
        }
    }
}
