package com.projectj2ee.travel_server.repository;

import com.projectj2ee.travel_server.dto.enums.StatusBooking;
import com.projectj2ee.travel_server.entity.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer> {
    Page<Booking> findAll(Pageable pageable);

    Optional<Booking> findByUser_IdAndStatus(int id, StatusBooking status);


    boolean existsById(int id);
}
