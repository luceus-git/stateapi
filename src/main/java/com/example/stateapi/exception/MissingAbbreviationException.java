package com.example.stateapi.exception;

public class MissingAbbreviationException extends RuntimeException {
    public MissingAbbreviationException(String message) {
        super(message);
    }
}

