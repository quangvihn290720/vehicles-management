package com.java.vehicles_management.mapper;

import com.java.vehicles_management.dto.request.MaintenanceRecordCreationRequest;
import com.java.vehicles_management.dto.request.MaintenanceRecordUpdateRequest;
import com.java.vehicles_management.dto.response.MaintenanceRecordResponse;
import com.java.vehicles_management.entity.MaintenanceRecords;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MaintenanceRecordMapper {
    @Mapping(source = "request.vehicles.users.id", target = "vehicles.users.id")
    @Mapping(source = "request.vehicles.id", target = "vehicles.id")
    MaintenanceRecords toMaintenanceRecord(MaintenanceRecordCreationRequest request);

    @Mapping(source = "maintenanceRecord.vehicles.users.id", target = "vehicles.users.id")
    @Mapping(source = "maintenanceRecord.vehicles.id",  target = "vehicles.id")
    MaintenanceRecordResponse toMaintenanceRecordResponse(MaintenanceRecords maintenanceRecord);

    @Mapping(target = "vehicles", ignore = true)
    void updateMaintenanceRecord(@MappingTarget MaintenanceRecords maintenanceRecord, MaintenanceRecordUpdateRequest request);
}
