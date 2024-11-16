package com.pocketdoctor.repository;

import com.pocketdoctor.model.DoctorData;
import com.pocketdoctor.model.HospitalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HospitalRepository extends JpaRepository<HospitalData, Integer> {

    Optional<HospitalData> findByUsernameAndPassword(String username,String password);

}
