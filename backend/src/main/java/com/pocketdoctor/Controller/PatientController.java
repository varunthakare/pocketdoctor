package com.pocketdoctor.Controller;

import com.pocketdoctor.model.PatientData;
import com.pocketdoctor.services.OtpService;
import com.pocketdoctor.services.UserServices;
import com.pocketdoctor.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class PatientController {

    private static final Logger logger = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    private UserServices userServices;

    @Autowired
    private OtpService otpService;

    @Autowired
    private OtpController otpController;

    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody PatientData patientData) {
        String otp = otpService.generateOtp();
        String mobileno = patientData.getMobileno();
        PatientData existingUser = userServices.findByMobileNo(mobileno);

        if (existingUser != null) {
            existingUser.setOtp(otp);
            userServices.updateUser(existingUser, mobileno);
        } else {
            PatientData newUser = new PatientData();
            newUser.setMobileno(mobileno);
            newUser.setOtp(otp);
            userServices.saveUser(newUser);
        }

        otpController.sendOtp(mobileno, otp);

        logger.info("OTP sent to mobile number: {}", mobileno);
        System.out.println("OTP = "+otp);
        return new ResponseEntity<>("OTP sent successfully", HttpStatus.OK);
    }

    @PostMapping("/login/otp-verify")
    public ResponseEntity<Map<String, Object>> loginUserVerifyOtp(@RequestBody PatientData patientData) {
        Map<String, Object> response = new HashMap<>();

        // Authenticate the user with the mobile number and OTP
        boolean authenticated = userServices.authenticateUser(patientData.getMobileno(), patientData.getOtp());
        if (!authenticated) {
            response.put("message", "Invalid OTP");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        // Find the existing user by mobile number
        PatientData existingUser = userServices.findByMobileNo(patientData.getMobileno());
        if (existingUser == null) {
            response.put("message", "User not found");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        // Create JWT token claims
        Map<String, Object> claims = new HashMap<>();
        claims.put("name", existingUser.getName());
        claims.put("role", existingUser.getType());
        claims.put("mobile", existingUser.getMobileno());

        // Generate JWT token
        String jwtToken = jwtUtil.generateTokenAndStore(claims, existingUser.getMobileno());

        // Add token to the response
        response.put("token", jwtToken);
        response.put("message", "Login successful");

        // Check user type and set the appropriate page (farmer or consumer)
        String userType = (existingUser.getType() != null) ? existingUser.getType() : "unknown";
        if ("farmer".equalsIgnoreCase(userType)) {
            response.put("userType", "farmer");
            response.put("isNewUser", false); // Existing user
        } else if ("consumer".equalsIgnoreCase(userType)) {
            response.put("userType", "consumer");
            response.put("isNewUser", false); // Existing user
        } else {
            response.put("userType", "unknown");
            response.put("isNewUser", true); // Handle unknown or unregistered users
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerUser(@RequestBody PatientData patientData) {
        Map<String, Object> response = new HashMap<>();

        // Check if the user already exists based on the mobile number
        PatientData existingUser = userServices.findByMobileNo(patientData.getMobileno());

        // If no existing user is found, register a new user
        if (existingUser == null) {
            // Save new user
            //KYC status to false by default
            String otp = otpService.generateOtp();
            patientData.setOtp(otp);
            userServices.saveUser(patientData);

            System.out.println(otp);



            // Generate JWT token with user details
            Map<String, Object> claims = new HashMap<>();
            claims.put("name", patientData.getName());
            claims.put("role", patientData.getType());
            claims.put("mobile", patientData.getMobileno());

            // Generate JWT token
            String jwtToken = jwtUtil.generateTokenAndStore(claims, patientData.getMobileno());

            // Return response with token and user details
            response.put("message", "User registered successfully");
            response.put("role", patientData.getType()); // 'Farmer' or 'Consumer'
            response.put("token", jwtToken); // Include token in the response

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }

        // If the user's name and type are not set, update user details
        if (existingUser.getName() == null && existingUser.getType() == null) {
            existingUser.setName(patientData.getName());
            existingUser.setType(patientData.getType());
            userServices.updateUser(existingUser, patientData.getMobileno());

            // Generate updated JWT token
            Map<String, Object> claims = new HashMap<>();
            claims.put("name", existingUser.getName());
            claims.put("role", existingUser.getType());
            claims.put("mobile", existingUser.getMobileno());


            String jwtToken = jwtUtil.generateTokenAndStore(claims, existingUser.getMobileno());

            // Return response with token and user details
            response.put("message", "User registered successfully");
            response.put("role", existingUser.getType()); // 'Farmer' or 'Consumer'
            response.put("token", jwtToken); // Include token in the response

            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        // If the user is already registered, return an appropriate message
        response.put("message", "User already registered");
        response.put("role", existingUser.getType()); // 'Farmer' or 'Consumer'

        // Include updated JWT token for already registered users
        Map<String, Object> claims = new HashMap<>();
        claims.put("name", existingUser.getName());
        claims.put("role", existingUser.getType());
        claims.put("mobile", existingUser.getMobileno());


        String jwtToken = jwtUtil.generateTokenAndStore(claims, existingUser.getMobileno());
        response.put("token", jwtToken); // Include token in the response

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
