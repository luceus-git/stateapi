package com.example.stateapi.controller;

import com.example.stateapi.exception.StateNotFoundException;
import com.example.stateapi.exception.MissingAbbreviationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    // pathing error
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error: An unexpected error occurred.");
    }

    // no abbreviation (ex: http://localhost:8080/state/)
    @ExceptionHandler(MissingAbbreviationException.class)
    public ResponseEntity<?> handleMissingAbbreviation(MissingAbbreviationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + ex.getMessage());
    }

    // state abbreviation not found (ex: http://localhost:8080/state/zz)
    @ExceptionHandler(StateNotFoundException.class)
    public ResponseEntity<?> handleStateNotFound(StateNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(org.springframework.web.servlet.NoHandlerFoundException.class)
    public ResponseEntity<?> handleNotFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("404 Error: The requested endpoint was not found.");
    }
}
