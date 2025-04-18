package com.example.stateapi;

import com.example.stateapi.controller.StateController;
import com.example.stateapi.service.StateService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StateController.class)  // Load only the controller layer
public class StateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean  // Automatically creates and injects a mock of StateService
    private StateService stateService;

    @Test // valid abbreviation
    public void testGetStateName_Success() throws Exception {
        // Mock the service method call
        when(stateService.getStateFullName("NY")).thenReturn("New York");

        // Perform the request and assert the response
        mockMvc.perform(get("/state/NY"))
                .andExpect(status().isOk())
                .andExpect(content().string("New York"));

        // Verify that the service method was called exactly once
        verify(stateService, times(1)).getStateFullName("NY");
    }

    @Test // invalid abbreviation
    public void testGetStateName_InvalidInput() throws Exception {
        mockMvc.perform(get("/state/INVALID"))
                .andExpect(status().isBadRequest());
    }

    @Test // invalid abbreviation (1 letter or number)
    public void testGetStateName_SingleInput() throws Exception {
        mockMvc.perform(get("/state/1"))
                .andExpect(status().isBadRequest());
    }

    @Test // abbreviation not found
    public void testGetStateName_NotFound() throws Exception {
        when(stateService.getStateFullName("NY")).thenReturn(null);

        mockMvc.perform(get("/state/NY"))
                .andExpect(status().isNotFound());
    }

    @Test // null abbreviation
    public void testGetStateName_NullInput() throws Exception {
        mockMvc.perform(get("/state/"))
                .andExpect(status().isBadRequest());
    }
}
