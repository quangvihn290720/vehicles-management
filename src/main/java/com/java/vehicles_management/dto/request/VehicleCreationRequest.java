package com.java.vehicles_management.dto.request;

import com.java.vehicles_management.entity.Users;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VehicleCreationRequest {
    String id;
    String make;
    String model;
    LocalDate year;
    String type;
    Users users;
}
