package com.projectj2ee.travel_server.repository;

import com.projectj2ee.travel_server.entity.Activity;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActivityRepository extends JpaRepository<Activity,Long> {
    Optional<Activity> findByLevel(String level);
}
