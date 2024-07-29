package com.java.vehicles_management.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Collection;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Vehicles {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String make;
    String model;
    LocalDate year;
    String type;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Users users;
}
