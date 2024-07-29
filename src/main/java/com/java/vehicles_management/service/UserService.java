package com.java.vehicles_management.service;

import com.java.vehicles_management.enums.Role;
import com.java.vehicles_management.dto.request.UserCreationRequest;
import com.java.vehicles_management.dto.request.UserUpdateRequest;
import com.java.vehicles_management.dto.response.UserResponse;
import com.java.vehicles_management.entity.Users;
import com.java.vehicles_management.exception.AppException;
import com.java.vehicles_management.exception.ErrorCode;
import com.java.vehicles_management.mapper.UserMapper;
import com.java.vehicles_management.repository.RoleRepository;
import com.java.vehicles_management.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.java.vehicles_management.enums.Role.USER;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    UserRepository userRepository;
    RoleRepository roleRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;


    public UserResponse createRequest(UserCreationRequest request){
        if (userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);

        Users users = userMapper.toUser(request);
        users.setPassword(passwordEncoder.encode(request.getPassword()));

        var roles = roleRepository.findAllById(Collections.singleton("USER"));
        users.setRoles(new HashSet<>(roles));

        return userMapper.toUserResponse(userRepository.save(users));
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request) {
        Users users = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userMapper.updateUser(users, request);
        users.setPassword(passwordEncoder.encode(request.getPassword()));

        var roles = roleRepository.findAllById(request.getRoles());
        users.setRoles(new HashSet<>(roles));

        return userMapper.toUserResponse(userRepository.save(users));
    }

    public void deleteUser(String userId){
        userRepository.deleteById(userId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getUsers(){
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse).toList();
    }

    @PostAuthorize("returnObject.username == authentication.name")
    public UserResponse getUser(String id){
        return userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found")));
    }

    public UserResponse getMyInfo(){
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        Users user = userRepository.findByUsername(name).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return userMapper.toUserResponse(user);
    }
}
