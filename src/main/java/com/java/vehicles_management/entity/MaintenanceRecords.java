package com.java.vehicles_management.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class MaintenanceRecords {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String description;
    LocalDate serviceDate;
    BigDecimal cost;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Vehicles vehicles;

}
