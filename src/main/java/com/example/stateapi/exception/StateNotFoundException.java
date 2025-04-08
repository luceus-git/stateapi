package com.example.stateapi.exception;

public class StateNotFoundException extends RuntimeException {
    public StateNotFoundException(String abbreviation) {
        super("State abbreviation not found: " + abbreviation);
    }
}


