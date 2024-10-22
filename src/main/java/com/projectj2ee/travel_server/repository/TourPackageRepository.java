package com.projectj2ee.travel_server.repository;

import com.projectj2ee.travel_server.entity.TourPackage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourPackageRepository extends JpaRepository<TourPackage,Long> {
}
