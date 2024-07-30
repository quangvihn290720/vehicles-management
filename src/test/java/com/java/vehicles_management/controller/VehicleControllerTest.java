package com.java.vehicles_management.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.java.vehicles_management.dto.request.VehicleCreationRequest;
import com.java.vehicles_management.dto.response.VehicleResponse;
import com.java.vehicles_management.entity.Users;
import com.java.vehicles_management.service.VehicleService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@SpringBootTest
@AutoConfigureMockMvc
public class VehicleControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private VehicleService vehicleService;

    private VehicleCreationRequest request;
    private VehicleResponse vehicleResponse;
    private LocalDate localDate;
    private Users user;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @BeforeEach
    void initData() {
        localDate = LocalDate.of(1990, 1, 1);

        user = Users.builder()
                .id("cf0600f538b3")
                .username("javadev2")
                .firstName("Java Test 1")
                .lastName("Java Test 1")
                .build();

        request = VehicleCreationRequest.builder()
                .make("iron")
                .model("xxxx1")
                .type("Car")
                .year(localDate)
                .users(user)
                .build();

        vehicleResponse = VehicleResponse.builder()
                .id("cf0600f538b3")
                .make("iron")
                .model("xxxx1")
                .type("Car")
                .year(localDate)
                .users(user)
                .build();
    }

//    @Test
//    void createUser_validRequest_success() throws Exception {
//        // GIVEN
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule());
//        String content = objectMapper.writeValueAsString(request);
//
//        Mockito.when(vehicleService.createVehicle(ArgumentMatchers.any()))
//                .thenReturn(vehicleResponse);
//
//        // WHEN, THEN
//        mockMvc.perform(MockMvcRequestBuilders
//                        .post("/vehicles")
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .content(content))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("code")
//                        .value(1000))
//                .andExpect(MockMvcResultMatchers.jsonPath("result.id")
//                        .value("cf0600f538b3")
//                );
//    }
}
