package com.example.stateapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class StateController {

    // State map
    private static final Map<String, String> states = new HashMap<>();
    static {
        states.put("TN", "Tennessee");
        states.put("IL", "Illinois");
        states.put("CA", "California");
        states.put("NY", "New York");
    }

    // ✅ Handles requests to /state/{abbreviation}
    @GetMapping("/state/{abbreviation}")
    public ResponseEntity<?> getStateName(@PathVariable String abbreviation) {
        // Validate abbreviation length (must be 2 characters)
        if (abbreviation.length() != 2) {
            return ResponseEntity.badRequest().body("Error: State abbreviation must be exactly 2 letters.");
        }

        // Convert to uppercase for case-insensitive lookup
        abbreviation = abbreviation.toUpperCase();

        // Retrieve the state name or return 404 if not found
        String stateName = states.get(abbreviation);
        if (stateName == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: State abbreviation not recognized.");
        }

        return ResponseEntity.ok(stateName);
    }

    // ✅ Handles requests to /state/ (missing abbreviation)
    @GetMapping(value = {"/state/"})
    public ResponseEntity<?> handleMissingAbbreviation() {
        return ResponseEntity.badRequest().body("Error: State abbreviation is required.");
    }
}


