package com.example.stateapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class StateController {

    // state hmap
    private static final Map<String, String> states = new HashMap<>();
    static {
        states.put("TN", "Tennessee");
        states.put("IL", "Illinois");
        states.put("CA", "California");
        states.put("NY", "New York");
        // Add database?
    }

    @GetMapping("/state/{abbreviation}") // map pathing
    public String getStateName(@PathVariable("abbreviation") String abbreviation) {
        // handle case sensitivity
        String stateName = states.get(abbreviation.toUpperCase());
        if (stateName == null) {
            return "State not found"; // return error code HTTP 404?
        }
        return stateName;
    }
}

