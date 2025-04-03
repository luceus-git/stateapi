package com.example.stateapi.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StateService {

    private static final Map<String, String> states = new HashMap<>();

    static {
        states.put("TN", "Tennessee");
        states.put("IL", "Illinois");
        states.put("CA", "California");
        states.put("NY", "New York");
    }

    public String getStateFullName(String abbreviation) {
        if (abbreviation == null || abbreviation.length() != 2) {
            return null; // Simulating a "not found" scenario
        }
        return states.get(abbreviation.toUpperCase());
    }
}

