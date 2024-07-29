package com.java.vehicles_management;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.java.vehicles_management.dto.request.UserCreationRequest;
import com.java.vehicles_management.dto.response.UserResponse;
import com.java.vehicles_management.service.UserService;
import lombok.extern.slf4j.Slf4j;
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


@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private UserCreationRequest request;
    private UserResponse userResponse;

    @BeforeEach
    void initData(){
        request = UserCreationRequest.builder()
                .username("java1")
                .firstName("Java Test 1")
                .lastName("Java Test 1")
                .password("12345678")
                .build();

        userResponse = UserResponse.builder()
                .id("cf0600f538b3")
                .username("java1")
                .firstName("Java Test 1")
                .lastName("Java Test 1")
                .build();
    }

    @Test
    void createUser_validRequest_success() throws Exception {
        // GIVEN
        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(request);

        Mockito.when(userService.createRequest(ArgumentMatchers.any()))
                .thenReturn(userResponse);

        // WHEN, THEN
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users/registration")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code")
                        .value(1000))
                .andExpect(MockMvcResultMatchers.jsonPath("result.id")
                        .value("cf0600f538b3")
                );
    }

}
