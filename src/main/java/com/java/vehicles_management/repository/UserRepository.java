package com.java.vehicles_management.repository;

import com.java.vehicles_management.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {
    boolean existsByUsername(String username);
    Optional<Users> findByUsername(String username);
}
