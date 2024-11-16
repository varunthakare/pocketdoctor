package com.pocketdoctor.repository;

import com.pocketdoctor.model.HospitalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends JpaRepository<HospitalData, Integer> {
}
