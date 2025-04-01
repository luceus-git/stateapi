package com.example.stateapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/state")
public class StateController {

    private static final Map<String, String> STATES = Map.of(
            "TN", "Tennessee",
            "NY", "New York",
            "CA", "California",
            "TX", "Texas"
    );

    @GetMapping("/{abbr}")
    public ResponseEntity<String> getStateName(@PathVariable String abbr) {
        String stateName = STATES.get(abbr.toUpperCase());
        return (stateName != null)
                ? ResponseEntity.ok(stateName)
                : ResponseEntity.badRequest().body("Invalid state abbreviation");
    }
}

