package com.java.vehicles_management.mapper;

import com.java.vehicles_management.dto.request.VehicleCreationRequest;
import com.java.vehicles_management.dto.request.VehicleUpdateRequest;
import com.java.vehicles_management.dto.response.VehicleResponse;
import com.java.vehicles_management.entity.Vehicles;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface VehicleMapper {
    @Mapping(source = "request.users.id", target = "users.id")
    Vehicles toVehicle(VehicleCreationRequest request);

    @Mapping(source = "vehicle.users.id", target = "users.id")
    VehicleResponse toVehicleResponse(Vehicles vehicle);

    void updateVehicle(@MappingTarget Vehicles vehicle, VehicleUpdateRequest request);

}
