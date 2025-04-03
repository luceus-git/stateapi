package com.example.stateapi.controller;

import com.example.stateapi.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StateController {

    private final StateService stateService;

    @Autowired
    public StateController(StateService stateService) {
        this.stateService = stateService;
    }

    @GetMapping("/state/{abbreviation}")
    public ResponseEntity<String> getStateName(@PathVariable String abbreviation) {
        // Validate the abbreviation: Ensure it's exactly 2 characters long
        if (abbreviation == null || abbreviation.length() != 2) {
            return ResponseEntity.badRequest().body("Invalid state abbreviation.");
        }

        // Fetch the state full name using the service
        String stateName = stateService.getStateFullName(abbreviation.toUpperCase());

        // Check if the state exists in the map
        if (stateName == null) {
            return ResponseEntity.notFound().build();  // Return 404 if state not found
        }

        return ResponseEntity.ok(stateName);  // Return the state name if found
    }


}
