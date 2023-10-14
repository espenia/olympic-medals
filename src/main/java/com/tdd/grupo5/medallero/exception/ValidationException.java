package com.tdd.grupo5.medallero.exception;


public class ValidationException extends BadRequestException {

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, String error) {
        super(message, error);
    }
}
