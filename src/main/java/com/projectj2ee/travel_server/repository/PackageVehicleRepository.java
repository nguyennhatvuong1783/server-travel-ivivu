package com.projectj2ee.travel_server.repository;

import com.projectj2ee.travel_server.dto.Ids.PackageVehicleIds;
import com.projectj2ee.travel_server.entity.PackageVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageVehicleRepository extends JpaRepository<PackageVehicle, PackageVehicleIds> {
}
