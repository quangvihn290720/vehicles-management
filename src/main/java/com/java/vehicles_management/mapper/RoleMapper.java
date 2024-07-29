package com.java.vehicles_management.mapper;

import com.java.vehicles_management.dto.request.RoleRequest;
import com.java.vehicles_management.dto.response.RoleResponse;
import com.java.vehicles_management.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}