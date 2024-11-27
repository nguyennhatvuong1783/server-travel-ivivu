package com.projectj2ee.travel_server.repository;

import com.projectj2ee.travel_server.dto.Ids.PackageFeatureIds;
import com.projectj2ee.travel_server.entity.PackageFeature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageFeatureRepository extends JpaRepository<PackageFeature, PackageFeatureIds> {
}
