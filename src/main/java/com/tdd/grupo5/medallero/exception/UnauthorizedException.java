package com.tdd.grupo5.medallero.exception;

import org.springframework.http.HttpStatus;


public class UnauthorizedException extends BaseAPIException {

    public UnauthorizedException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }

    public UnauthorizedException(String message, String error) {
        super(message, HttpStatus.UNAUTHORIZED, error);
    }
}
