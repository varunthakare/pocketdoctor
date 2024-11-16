package com.pocketdoctor.repository;

import com.pocketdoctor.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {

    int countByHospitalId(String hospitalId);
}
