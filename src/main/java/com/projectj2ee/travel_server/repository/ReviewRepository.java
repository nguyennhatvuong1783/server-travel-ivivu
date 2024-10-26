package com.projectj2ee.travel_server.repository;

import com.projectj2ee.travel_server.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findByPackageId(int id);
}
