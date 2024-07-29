package com.java.vehicles_management.controller;


import com.java.vehicles_management.dto.request.ApiResponse;
import com.java.vehicles_management.dto.request.VehicleCreationRequest;
import com.java.vehicles_management.dto.request.VehicleUpdateRequest;
import com.java.vehicles_management.dto.response.VehicleResponse;
import com.java.vehicles_management.service.VehicleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class VehicleController {

    VehicleService vehicleService;

    @PostMapping
    ApiResponse<VehicleResponse> createVehicle(@RequestBody VehicleCreationRequest request){
        return ApiResponse.<VehicleResponse>builder()
                .result(vehicleService.createVehicle(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<VehicleResponse>> getAll(){
        return ApiResponse.<List<VehicleResponse>>builder()
                .result(vehicleService.getAllVehicles())
                .build();
    }

    @GetMapping("/{vehicleId}")
    ApiResponse<VehicleResponse> getVehicle(@PathVariable("vehicleId") String vehicleId){
        return ApiResponse.<VehicleResponse>builder()
                .result(vehicleService.getVehicle(vehicleId))
                .build();
    }

    @PutMapping("/{vehicleId}")
    ApiResponse<VehicleResponse> updateVehicle(@PathVariable("vehicleId") String vehicleId,
        @RequestBody VehicleUpdateRequest request){
        return ApiResponse.<VehicleResponse>builder()
                .result(vehicleService.updateVehicle(vehicleId, request))
                .build();
    }
}
