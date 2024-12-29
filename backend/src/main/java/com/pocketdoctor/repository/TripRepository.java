package com.pocketdoctor.repository;

import com.pocketdoctor.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    // You can add custom queries here if needed (e.g., find by driverId or patientId)
}
