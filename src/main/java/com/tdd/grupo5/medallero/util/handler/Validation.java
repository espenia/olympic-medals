package com.tdd.grupo5.medallero.util.handler;

public final class Validation {
    private final String property;
    private final String message;

    public Validation(String property, String message) {
        this.property = property;
        this.message = message;
    }

    public String getProperty() {
        return this.property;
    }

    public String getMessage() {
        return this.message;
    }
}