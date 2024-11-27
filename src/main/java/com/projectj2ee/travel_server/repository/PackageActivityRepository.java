package com.projectj2ee.travel_server.repository;

import com.projectj2ee.travel_server.dto.Ids.PackageActivityIds;
import com.projectj2ee.travel_server.entity.PackageActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageActivityRepository extends JpaRepository<PackageActivity, PackageActivityIds> {
}
