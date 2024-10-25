package com.projectj2ee.travel_server.repository;

import com.projectj2ee.travel_server.entity.InvalidatedToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvalidatedRepository extends JpaRepository<InvalidatedToken,Long> {
    boolean existsById(String id);
}
