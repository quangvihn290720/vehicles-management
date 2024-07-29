package com.java.vehicles_management.service;

import com.java.vehicles_management.dto.request.MaintenanceRecordCreationRequest;
import com.java.vehicles_management.dto.request.MaintenanceRecordUpdateRequest;
import com.java.vehicles_management.dto.response.MaintenanceRecordResponse;
import com.java.vehicles_management.entity.MaintenanceRecords;
import com.java.vehicles_management.exception.AppException;
import com.java.vehicles_management.exception.ErrorCode;
import com.java.vehicles_management.mapper.MaintenanceRecordMapper;
import com.java.vehicles_management.repository.MaintenanceRecordRepository;
import com.java.vehicles_management.repository.VehicleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class MaintenanceRecordService {
    MaintenanceRecordRepository maintenanceRecordRepository;
    VehicleRepository vehicleRepository;
    MaintenanceRecordMapper maintenanceRecordMapper;

    public MaintenanceRecordResponse createMaintenanceRecord(MaintenanceRecordCreationRequest request){
        var vehicle = vehicleRepository.findById(request.getVehicles().getId()).orElseThrow(() -> new AppException(ErrorCode.VEHICLE_NOT_EXISTED));
        MaintenanceRecords maintenanceRecord = maintenanceRecordMapper.toMaintenanceRecord(request);
        maintenanceRecord.setVehicles(vehicle);
        return maintenanceRecordMapper.toMaintenanceRecordResponse(maintenanceRecordRepository.save(maintenanceRecord));
    }

    public MaintenanceRecordResponse updateMaintenanceRecord(String recordId, MaintenanceRecordUpdateRequest request){
        MaintenanceRecords maintenanceRecord = maintenanceRecordRepository.findById(recordId)
                .orElseThrow(() -> new AppException(ErrorCode.MAINTENANCE_RECORD_NOT_EXISTED));
        maintenanceRecordMapper.updateMaintenanceRecord(maintenanceRecord, request);
        return maintenanceRecordMapper.toMaintenanceRecordResponse(maintenanceRecordRepository.save(maintenanceRecord));
    }

    public MaintenanceRecordResponse getMaintenanceRecord(String maintenanceRecordId){
        return maintenanceRecordMapper.toMaintenanceRecordResponse(maintenanceRecordRepository.findById(maintenanceRecordId)
                .orElseThrow(()-> new AppException(ErrorCode.MAINTENANCE_RECORD_NOT_EXISTED)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<MaintenanceRecordResponse> getMaintenanceRecords() {
        return maintenanceRecordRepository.findAll().stream()
                .map(maintenanceRecordMapper::toMaintenanceRecordResponse).toList();
    }

}
