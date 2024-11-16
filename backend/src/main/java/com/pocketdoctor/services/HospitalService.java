package com.pocketdoctor.services;

import com.pocketdoctor.model.DoctorData;
import com.pocketdoctor.model.HospitalData;
import com.pocketdoctor.repository.DoctorRepository;
import com.pocketdoctor.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    public List<DoctorData> getDoctorsByHospitalId(String hospitalId) {

        List<DoctorData> verifiedData = new ArrayList<>();
        List<DoctorData> data = doctorRepository.findByHospitalId(hospitalId);

        for(int i=0;i<data.size();i++){

            if(data.get(i).isVerify()){
                verifiedData.add(data.get(i));
            }

        }

        return verifiedData;
    }
    public HospitalData findByUsernameAndPassword(String username,String password) {

        return hospitalRepository.findByUsernameAndPassword(username,password).orElse(null);
    }

    public Long getHospitalIdByUsername(String username) {

        return hospitalRepository.findByUsername(username);
    }
}
