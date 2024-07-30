package com.java.vehicles_management.service;


import com.java.vehicles_management.dto.request.UserCreationRequest;
import com.java.vehicles_management.entity.Users;
import com.java.vehicles_management.exception.AppException;
import com.java.vehicles_management.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    private UserCreationRequest request;
    private Users user;

    @BeforeEach
    void initData() {
        request = UserCreationRequest.builder()
                .username("javadev2")
                .firstName("Java Test 1")
                .lastName("Java Test 1")
                .password("12345678")
                .build();

//        userResponse = UserResponse.builder()
//                .id("cf0600f538b3")
//                .username("javadev2")
//                .firstName("Java Test 1")
//                .lastName("Java Test 1")
//                .build();

        user = Users.builder()
                .id("cf0600f538b3")
                .username("javadev2")
                .firstName("Java Test 1")
                .lastName("Java Test 1")
                .build();
    }

    @Test
    void createUser_validRequest_success() {
        // GIVEN
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.save(any())).thenReturn(user);

        // WHEN
        var response = userService.createRequest(request);
        // THEN

        Assertions.assertThat(response.getId()).isEqualTo("cf0600f538b3");
        Assertions.assertThat(response.getUsername()).isEqualTo("javadev2");
    }

    @Test
    void createUser_userExisted_fail() {
        // GIVEN
        when(userRepository.existsByUsername(anyString())).thenReturn(true);

        // WHEN
        var exception = assertThrows(AppException.class, () -> userService.createRequest(request));

        // THEN
        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1002);
    }

    @Test
    @WithMockUser(username = "javadev2")
    void getMyInfo_valid_success() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));

        var response = userService.getMyInfo();

        Assertions.assertThat(response.getUsername()).isEqualTo("javadev2");
        Assertions.assertThat(response.getId()).isEqualTo("cf0600f538b3");
    }

    @Test
    @WithMockUser(username = "javadev2")
    void getMyInfo_userNotFound_error() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.ofNullable(null));

        // WHEN
        var exception = assertThrows(AppException.class, () -> userService.getMyInfo());

        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1005);
    }
}