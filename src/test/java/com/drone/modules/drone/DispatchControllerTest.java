package com.drone.modules.drone;

import com.drone.api.common.model.ResponseDto;
import com.drone.api.drone.controller.DispatchController;
import com.drone.api.drone.model.Drone;
import com.drone.api.drone.service.DispatchService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DispatchController.class)
public class DispatchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DispatchService dispatchService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        this.objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false); // it's true by default
    }

    @Test
    public void registerDroneTest() throws Exception {
        Drone expectedDrone = DroneHelper.getDroneRequest();
        Drone actualDrone = DroneHelper.getDroneResponse();

        when(this.dispatchService.register(expectedDrone)).thenReturn(new ResponseDto(actualDrone));

        this.mockMvc.perform(MockMvcRequestBuilders.post("/drone/register")
                        .content(this.objectMapper.writeValueAsString(expectedDrone))
                        .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .header("Accept", MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.size()", Matchers.is(2)))
                .andExpect(content().json(this.objectMapper.writeValueAsString(new ResponseDto(actualDrone))));
    }
}
