package com.java.vehicles_management.dto.response;

import com.java.vehicles_management.entity.Vehicles;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MaintenanceRecordResponse {
    String id;
    String description;
    LocalDate serviceDate;
    BigDecimal cost;
    Vehicles vehicles;
}
