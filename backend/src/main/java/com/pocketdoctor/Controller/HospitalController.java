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

    @Autowired
    private GoogleMapsService googleMapsService;

    @PostMapping("/add")
    public ResponseEntity<HospitalData> addHospital(@RequestBody HospitalData hospitalData) {
        try {

            if (hospitalData.getLocation() == null || !hospitalData.getLocation().contains(" ")) {
                throw new IllegalArgumentException("Invalid location format. Expected 'latitude longitude'.");
            }

            String[] location = hospitalData.getLocation().split(" ");
            double latitude = Double.parseDouble(location[0]);
            double longitude = Double.parseDouble(location[1]);

            String liveLocationUrl = googleMapsService.generateLiveLocationUrl(latitude, longitude);
            hospitalData.setLocation(liveLocationUrl);

            HospitalData savedHospital = hospitalRepository.save(hospitalData);
            return new ResponseEntity<>(savedHospital, HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {
            System.err.println("Invalid input: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        } catch (Exception e) {

            System.err.println("Error occurred while adding hospital: " + e.getMessage());
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

    @GetMapping("/dashboard/{username}")
    public ResponseEntity<Map<String, Object>> getDashboardData(@PathVariable("username") String username) {
        Integer hospitalId = hospitalService.getHospitalId(username); // Ensure this returns a valid Integer or throws an exception

        if (hospitalId == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Hospital ID not found"));
        }

        String hospitalName = hospitalService.getHospitalName(String.valueOf(hospitalId));
        int totalDoctors = doctorService.getTotalDoctorsByHospital(String.valueOf(hospitalId));
        int totalPatients = appointmentService.getTotalPatientsByHospital(String.valueOf(hospitalId));

        Map<String, Object> dashboardData = new HashMap<>();
        dashboardData.put("ID", hospitalId);
        dashboardData.put("Name", hospitalName);
        dashboardData.put("totalDoctors", totalDoctors);
        dashboardData.put("totalPatients", totalPatients);

        return ResponseEntity.ok(dashboardData);
    }




}
