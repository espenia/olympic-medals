package com.tdd.grupo5.medallero.service;

import com.tdd.grupo5.medallero.entities.User;
import com.tdd.grupo5.medallero.exception.ValidationException;
import com.tdd.grupo5.medallero.repositories.UserRepository;
import com.tdd.grupo5.medallero.util.OAuth2AccessTokenUtils;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Service;

import static com.tdd.grupo5.medallero.util.Constants.INVALID_COMBINATION_USER_PASSWORD_ERROR;
import static com.tdd.grupo5.medallero.util.Constants.INVALID_COMBINATION_USER_PASSWORD_MSG;

@Service
public class LoginService {

    private UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public OAuth2AccessToken login(String userName, String password) {
        User user = userRepository.findByUserName(userName);
        if (user == null || user.getPassword().equals(password)) {
            throw new ValidationException(INVALID_COMBINATION_USER_PASSWORD_ERROR, INVALID_COMBINATION_USER_PASSWORD_MSG);
        }
        return OAuth2AccessTokenUtils.generateToken(user);
    }
}
