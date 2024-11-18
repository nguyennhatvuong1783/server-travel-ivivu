package com.projectj2ee.travel_server.repository;

import com.projectj2ee.travel_server.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,Integer> {
    Optional<Vehicle> findByName(String vehicle_name);
}
