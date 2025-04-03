package com.example.stateapi;

import com.example.stateapi.controller.StateController;
import com.example.stateapi.service.StateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StateController.class)  // Load only the controller layer
@ExtendWith(MockitoExtension.class)  // Enable Mockito support
public class StateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean  // Use @MockBean instead of @Mock so Spring injects the mock
    private StateService stateService;

    @Test
    public void testGetStateName_Success() throws Exception {
        when(stateService.getStateFullName("NY")).thenReturn("New York");

        mockMvc.perform(get("/state/NY"))
                .andExpect(status().isOk())
                .andExpect(content().string("New York"));

        verify(stateService, times(1)).getStateFullName("NY");  // Ensure the mock was called
    }

    @Test
    public void testGetStateName_InvalidInput() throws Exception {
        mockMvc.perform(get("/state/INVALID"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetStateName_NotFound() throws Exception {
        when(stateService.getStateFullName("ZZ")).thenReturn(null);

        mockMvc.perform(get("/state/ZZ"))
                .andExpect(status().isNotFound());
    }
}
