package com.pocketdoctor.repository;


import com.pocketdoctor.model.PatientData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<PatientData, Long> {

    Optional<PatientData> findByMobilenoAndOtp(String mobileno, String otp);

    Optional<PatientData> findByMobileno(String mobile);


}