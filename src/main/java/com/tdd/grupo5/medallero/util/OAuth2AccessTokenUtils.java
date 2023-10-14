package com.tdd.grupo5.medallero.util;

import com.tdd.grupo5.medallero.entities.User;
import org.springframework.security.oauth2.core.OAuth2AccessToken;

import java.time.Duration;
import java.util.Date;

public class OAuth2AccessTokenUtils {
    public static OAuth2AccessToken generateToken(User user) {
        return new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER,
                null,
                new Date().toInstant(),
                new Date().toInstant().plus(Duration.ofHours(2)));
    }
}
