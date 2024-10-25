package com.projectj2ee.travel_server.repository;

import com.projectj2ee.travel_server.entity.TourGuide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourGuideRepository extends JpaRepository<TourGuide,Long> {
}
