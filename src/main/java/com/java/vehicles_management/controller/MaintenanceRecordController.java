package com.java.vehicles_management.controller;

import com.java.vehicles_management.dto.request.*;
import com.java.vehicles_management.dto.response.MaintenanceRecordResponse;
import com.java.vehicles_management.service.MaintenanceRecordService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maintenance")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class MaintenanceRecordController {

    MaintenanceRecordService maintenanceRecordService;

    @PostMapping
    ApiResponse<MaintenanceRecordResponse> create(@RequestBody MaintenanceRecordCreationRequest request){
        return ApiResponse.<MaintenanceRecordResponse>builder()
                .result(maintenanceRecordService.createMaintenanceRecord(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<MaintenanceRecordResponse>> getMaintenanceRecords(){
        return ApiResponse.<List<MaintenanceRecordResponse>>builder()
                .result(maintenanceRecordService.getMaintenanceRecords())
                .build();
    }

    @GetMapping("/{recordId}")
    ApiResponse<MaintenanceRecordResponse> getMaintenanceRecord(@PathVariable("recordId") String recordId){
        return ApiResponse.<MaintenanceRecordResponse>builder()
                .result(maintenanceRecordService.getMaintenanceRecord(recordId))
                .build();
    }

    @PutMapping("/{recordId}")
    ApiResponse<MaintenanceRecordResponse> updateMaintenanceRecord(@PathVariable("recordId") String recordId,
        @RequestBody MaintenanceRecordUpdateRequest request){
        return ApiResponse.<MaintenanceRecordResponse>builder()
                .result(maintenanceRecordService.updateMaintenanceRecord(recordId, request))
                .build();
    }
}
