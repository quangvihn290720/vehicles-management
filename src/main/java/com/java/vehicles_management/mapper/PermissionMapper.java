package com.java.vehicles_management.mapper;

import com.java.vehicles_management.dto.request.PermissionRequest;
import com.java.vehicles_management.dto.response.PermissionResponse;
import com.java.vehicles_management.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
}