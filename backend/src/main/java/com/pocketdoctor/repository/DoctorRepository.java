package com.pocketdoctor.repository;

import com.pocketdoctor.model.DoctorData;
import com.pocketdoctor.model.PatientData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorData, Integer> {

    List<DoctorData> findByHospitalId(String hospitalId);

    Optional<DoctorData> findByMobilenoAndOtp(String mobileno, String otp);

    Optional<DoctorData> findByMobileno(String mobile);

}
