package com.java.vehicles_management.service;

import com.java.vehicles_management.constant.VMConstant;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.JavaScriptUtils;

import java.util.Collections;
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
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        MaintenanceRecords maintenanceRecord = maintenanceRecordRepository.findById(recordId)
                .orElseThrow(() -> new AppException(ErrorCode.MAINTENANCE_RECORD_NOT_EXISTED));

        boolean isAdmin = VMConstant.ADMIN.equalsIgnoreCase(authentication.getName());
        boolean isOwn   = maintenanceRecord.getVehicles().getId().equalsIgnoreCase(request.getVehicles().getId());
        if (isOwn || isAdmin) {
            maintenanceRecordMapper.updateMaintenanceRecord(maintenanceRecord, request);
            return maintenanceRecordMapper.toMaintenanceRecordResponse(maintenanceRecordRepository.save(maintenanceRecord));
        }
        throw new AppException(ErrorCode.UNAUTHORIZED_UPDATE);
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

    public List<MaintenanceRecordResponse> getAllMaintenanceRecordsByVehiclesId(String vehicleId) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        List<MaintenanceRecordResponse> maintenanceRecordResponses = maintenanceRecordRepository
                .findAll().stream().filter(rs->vehicleId.equalsIgnoreCase(rs.getVehicles().getId()))
                .map(maintenanceRecordMapper::toMaintenanceRecordResponse).toList();

        boolean isAdmin = VMConstant.ADMIN.equalsIgnoreCase(authentication.getName());
        boolean isEmpty = maintenanceRecordResponses.isEmpty();

        if (isAdmin || !isEmpty) {
            return maintenanceRecordResponses;
        }
        throw new AppException(ErrorCode.UNAUTHORIZED_VIEW);
    }

}
