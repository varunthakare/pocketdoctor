package com.pocketdoctor.Controller;

import com.pocketdoctor.model.LocationUpdate;
import com.pocketdoctor.model.Trip;
import com.pocketdoctor.model.TripRequest;
import com.pocketdoctor.repository.TripRepository;
import com.pocketdoctor.services.GoogleMapsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class GoogleMap {

    @Autowired
    private GoogleMapsService googleMapsService;

    @Autowired
    private TripRepository tripRepository;



    @PostMapping("/ambulance/trip/start")
    public ResponseEntity<String> startTrip(@RequestBody TripRequest tripRequest) {
        try {
            // Validate trip details
            if (tripRequest.getDriverId() == null || tripRequest.getPatientId() == null) {
                return new ResponseEntity<>("Driver or Patient ID is missing", HttpStatus.BAD_REQUEST);
            }

            // Create a new trip
            Trip newTrip = new Trip();
            newTrip.setDriverId(tripRequest.getDriverId());
            newTrip.setPatientId(tripRequest.getPatientId());
            newTrip.setStatus("Started");

            // Generate live location URL (using Google Maps or another service)
            String liveLocationUrl = googleMapsService.generateLiveLocationUrl(0.0, 0.0);  // Initial dummy location
            newTrip.setLiveLocationUrl(liveLocationUrl);

            // Save trip to the database
            Trip savedTrip = tripRepository.save(newTrip);

            return new ResponseEntity<>(savedTrip.getLiveLocationUrl(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error starting trip: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/ambulance/trip/{tripId}/location")
    public ResponseEntity<String> updateLocation(@PathVariable Long tripId, @RequestBody LocationUpdate locationUpdate) {
        try {
            // Validate incoming location data
            if (locationUpdate.getLatitude() == null || locationUpdate.getLongitude() == null) {
                return new ResponseEntity<>("Latitude and Longitude are required", HttpStatus.BAD_REQUEST);
            }

            // Find the existing trip by tripId
            Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new RuntimeException("Trip not found"));

            // Update the trip's location
            trip.setLatitude(locationUpdate.getLatitude());
            trip.setLongitude(locationUpdate.getLongitude());

            // Generate a new live location URL
            String liveLocationUrl = googleMapsService.generateLiveLocationUrl(locationUpdate.getLatitude(), locationUpdate.getLongitude());
            trip.setLiveLocationUrl(liveLocationUrl);

            // Save the updated trip
            tripRepository.save(trip);

            return new ResponseEntity<>("Location updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating location: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/ambulance/trip/{tripId}/stop")
    public ResponseEntity<String> stopTrip(@PathVariable Long tripId) {
        try {
            // Find the trip and mark as stopped
            Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new RuntimeException("Trip not found"));
            trip.setStatus("Completed");

            // Save the updated trip
            tripRepository.save(trip);

            return new ResponseEntity<>("Trip completed successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error stopping trip: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/ambulance/trip/{tripId}/location")
    public ResponseEntity<String> getLiveLocation(@PathVariable Long tripId) {
        try {
            // Find the trip by tripId
            Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new RuntimeException("Trip not found"));

            // Return the live location URL
            return new ResponseEntity<>(trip.getLiveLocationUrl(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error fetching live location: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
