package com.example.stateapi.controller;

import com.example.stateapi.exception.StateNotFoundException;
import com.example.stateapi.exception.MissingAbbreviationException;
import com.example.stateapi.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class StateController {

    private static final Logger logger = LoggerFactory.getLogger(StateController.class);
    private final StateService stateService;

    @Autowired
    public StateController(StateService stateService) {
        this.stateService = stateService;
    }

    @GetMapping("/state/")
    public ResponseEntity<String> handleMissingAbbreviation() {
        throw new MissingAbbreviationException("State abbreviation is required.");
    }

    @GetMapping("/state/{abbreviation}")
    public ResponseEntity<String> getStateName(@PathVariable String abbreviation) {
//        logger.info("Received request for abbreviation: {}", abbreviation);

        // Validate the abbreviation: Ensure it's exactly 2 characters long and not a number
        if (!abbreviation.toUpperCase().matches("[a-zA-Z]{2}")) {
//            logger.warn("Invalid abbreviation received: {}", abbreviation);
            return ResponseEntity.badRequest().body("Invalid state abbreviation.");
        }

        // Fetch the state full name using the service
        String stateName = stateService.getStateFullName(abbreviation.toUpperCase());

        // Check if the state exists in the map
        if (stateName == null) {
            // Throw a custom exception if not found
//            logger.error("State not found for abbreviation: {}", abbreviation);
            throw new StateNotFoundException(abbreviation);
        }

//        logger.debug("Resolved abbreviation {} to {}", abbreviation, stateName);
        return ResponseEntity.ok(stateName);  // Return the state name if found
    }
}

//System.out.println("Abbreviation received: " + abbreviation);