package com.pocketdoctor.services;

import com.pocketdoctor.model.Appointment;
import com.pocketdoctor.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    // Constructor-based injection (recommended)
    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public Appointment createAppointment(Appointment appointment) {
        // Save the appointment to the database
        return appointmentRepository.save(appointment);
    }
    public int getTotalPatientsByHospital(String hospitalId) {
        return appointmentRepository.countByHospitalId(hospitalId);  // Assuming you have this query method in the repository
    }
}
