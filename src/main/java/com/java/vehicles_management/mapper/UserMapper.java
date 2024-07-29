package com.java.vehicles_management.mapper;

import com.java.vehicles_management.dto.request.UserCreationRequest;
import com.java.vehicles_management.dto.request.UserUpdateRequest;
import com.java.vehicles_management.dto.response.UserResponse;
import com.java.vehicles_management.entity.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    Users toUser(UserCreationRequest request);

    UserResponse toUserResponse(Users users);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget Users users, UserUpdateRequest request);
}
