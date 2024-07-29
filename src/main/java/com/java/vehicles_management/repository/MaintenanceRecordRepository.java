package com.java.vehicles_management.repository;

import com.java.vehicles_management.entity.MaintenanceRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceRecordRepository extends JpaRepository<MaintenanceRecords, String> {
}

