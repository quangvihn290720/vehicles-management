package com.java.vehicles_management.service;

import com.java.vehicles_management.constant.VMConstant;
import com.java.vehicles_management.dto.request.VehicleCreationRequest;
import com.java.vehicles_management.dto.request.VehicleUpdateRequest;
import com.java.vehicles_management.dto.response.UserResponse;
import com.java.vehicles_management.dto.response.VehicleResponse;
import com.java.vehicles_management.entity.Users;
import com.java.vehicles_management.entity.Vehicles;
import com.java.vehicles_management.exception.AppException;
import com.java.vehicles_management.exception.ErrorCode;
import com.java.vehicles_management.mapper.VehicleMapper;
import com.java.vehicles_management.repository.RoleRepository;
import com.java.vehicles_management.repository.UserRepository;
import com.java.vehicles_management.repository.VehicleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VehicleService {


    VehicleRepository vehicleRepository;
    UserRepository userRepository;
    VehicleMapper vehicleMapper;

    public VehicleResponse createVehicle(VehicleCreationRequest request){
        if (vehicleRepository.existsByModel(request.getModel())) {
            throw new AppException(ErrorCode.VEHICLE_EXISTED);
        }
        var user = userRepository.findById(request.getUsers().getId()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        Vehicles vehicle = vehicleMapper.toVehicle(request);
        vehicle.setUsers(user);
        return vehicleMapper.toVehicleResponse(vehicleRepository.save(vehicle));
    }


    public VehicleResponse updateVehicle(String vehicleId, VehicleUpdateRequest request){
        Vehicles vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new AppException(ErrorCode.VEHICLE_NOT_EXISTED));
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = VMConstant.ADMIN.equalsIgnoreCase(authentication.getName());
        boolean isOwn   = vehicle.getUsers().getId().equalsIgnoreCase(request.getUsers().getId());
        if (isOwn || isAdmin) {
            var user = userRepository.findById(request.getUsers().getId()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
            vehicleMapper.updateVehicle(vehicle, request);
            vehicle.setUsers(user);
            return vehicleMapper.toVehicleResponse(vehicleRepository.save(vehicle));
        }
        throw new AppException(ErrorCode.UNAUTHORIZED_UPDATE);
    }

    public VehicleResponse getVehicle(String vehicleId){
        return vehicleMapper.toVehicleResponse(vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new AppException(ErrorCode.VEHICLE_NOT_EXISTED)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<VehicleResponse> getAllVehicles(){
        return vehicleRepository.findAll()
                .stream()
                .map(vehicleMapper::toVehicleResponse)
                .toList();
    }
}
