package com.java.vehicles_management.controller;

import com.java.vehicles_management.dto.request.ApiResponse;
import com.java.vehicles_management.dto.request.AuthenticationRequest;
import com.java.vehicles_management.dto.request.IntrospectRequest;
import com.java.vehicles_management.dto.response.AuthenticationResponse;
import com.java.vehicles_management.dto.response.IntrospectResponse;
import com.java.vehicles_management.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/log-in")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        var result = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder().result(result).build();
    }

}
