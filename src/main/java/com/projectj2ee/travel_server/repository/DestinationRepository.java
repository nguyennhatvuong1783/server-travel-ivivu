package com.projectj2ee.travel_server.repository;

import com.projectj2ee.travel_server.entity.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository extends JpaRepository<Destination,Long> {
}
