package com.java.vehicles_management.repository;

import com.java.vehicles_management.entity.Vehicles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicles, String> {
    boolean existsByModel(String model);
}
