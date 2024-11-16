package com.pocketdoctor.Controller;

import com.pocketdoctor.model.DoctorData;
import com.pocketdoctor.model.HospitalData;
import com.pocketdoctor.repository.DoctorRepository;
import com.pocketdoctor.repository.HospitalRepository;
import com.pocketdoctor.services.DoctorService;
import com.pocketdoctor.services.HospitalService;
import com.pocketdoctor.services.OtpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hospitals")
@CrossOrigin(origins = "*")
public class HospitalController {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private HospitalService hospitalService;

    private static final Logger logger = LoggerFactory.getLogger(HospitalController.class);

    @Autowired
    private OtpService otpService;

    @Autowired
    private OtpController otpController;

    @PostMapping("/add")
    public ResponseEntity<HospitalData> addHospital(@RequestBody HospitalData hospitalData) {
        try {
            HospitalData savedHospital = hospitalRepository.save(hospitalData);
            return new ResponseEntity<>(savedHospital, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginHospital(@RequestBody HospitalData hospitalData){
        String username = hospitalData.getUsername();
        String password= hospitalData.getPassword();
        HospitalData existingUser = hospitalService.findByUsernameAndPassword(username,password);

        if(existingUser != null){
            return new ResponseEntity<>("Login successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Wrong Password and username", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{hospitalId}")
    public ResponseEntity<List<DoctorData>> getDoctorsByHospitalId(@PathVariable String hospitalId) {
        try {
            List<DoctorData> doctors = hospitalService.getDoctorsByHospitalId(hospitalId);
            if (doctors.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(doctors, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
