package com.projectj2ee.travel_server.repository;

import com.projectj2ee.travel_server.entity.TourDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourDateRepository extends JpaRepository<TourDate,Integer> {
    Boolean existsById(int id);

    List<TourDate> findByTourPackage_Id(int packageId);
}
