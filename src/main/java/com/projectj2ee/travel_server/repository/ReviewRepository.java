package com.projectj2ee.travel_server.repository;

import com.projectj2ee.travel_server.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Integer> {
    List<Review> findByTourPackage_id(int id);
}
