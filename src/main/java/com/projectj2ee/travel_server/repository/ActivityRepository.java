package com.projectj2ee.travel_server.repository;

import com.projectj2ee.travel_server.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActivityRepository extends JpaRepository<Activity,Integer> {
    Optional<Activity> findByLevel(String level);
}
