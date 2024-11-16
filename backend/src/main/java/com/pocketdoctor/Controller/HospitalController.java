package com.pocketdoctor.Controller;

import com.pocketdoctor.model.DoctorData;
import com.pocketdoctor.model.HospitalData;
import com.pocketdoctor.repository.DoctorRepository;
import com.pocketdoctor.repository.HospitalRepository;
import com.pocketdoctor.services.*;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private UserServices patientService;

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

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/dashboard/{id}")
    public ResponseEntity<Map<String, Object>> getDashboardData(@PathVariable("id") String hospitalId) {
        // Fetch the hospitalId based on the username (modify this logic as needed)
        // String hospitalId = userService.getHospitalIdByUsername(username); // Example method to get hospitalId by username

        // Call service methods to get data based on hospitalId
        // int totalBeds = bedService.getTotalBeds(hospitalId); // Modify this if necessary
        int totalDoctors = doctorService.getTotalDoctorsByHospital(hospitalId); // Get total doctors by hospitalId
        int totalPatients = appointmentService.getTotalPatientsByHospital(hospitalId); // Get total patients by hospitalId

        // Prepare data to be returned in the response
        Map<String, Object> dashboardData = new HashMap<>();
        // dashboardData.put("totalBeds", totalBeds);
        dashboardData.put("totalDoctors", totalDoctors);
        dashboardData.put("totalPatients", totalPatients);

        // Return the data as a ResponseEntity
        return ResponseEntity.ok(dashboardData);
    }



}
