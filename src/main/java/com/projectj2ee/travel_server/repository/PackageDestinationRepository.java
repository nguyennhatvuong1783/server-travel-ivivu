package com.projectj2ee.travel_server.repository;

import com.projectj2ee.travel_server.dto.Ids.PackageDestinationIds;
import com.projectj2ee.travel_server.entity.PackageDestination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageDestinationRepository extends JpaRepository<PackageDestination, PackageDestinationIds> {
}
