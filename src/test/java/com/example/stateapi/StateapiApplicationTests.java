package com.example.stateapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class StateapiApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetStateName_ValidAbbreviation() throws Exception {
        mockMvc.perform(get("/state/NY"))
                .andExpect(status().isOk())
                .andExpect(content().string("New York"));
    }

    @Test
    public void testGetStateName_NotFound() throws Exception {
        mockMvc.perform(get("/state/ZZ"))
                .andExpect(status().isNotFound());
    }
}

