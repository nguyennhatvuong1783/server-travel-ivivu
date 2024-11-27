package com.projectj2ee.travel_server.repository;

import com.projectj2ee.travel_server.dto.Ids.PackageAccommodationIds;
import com.projectj2ee.travel_server.entity.PackageAccommodation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageAccommodationRepository extends JpaRepository<PackageAccommodation, PackageAccommodationIds> {
}
