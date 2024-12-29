package com.pocketdoctor.model;

public class TripRequest {

    private Long driverId;  // ID of the driver
    private Long patientId; // ID of the patient
    private Double startLatitude; // Starting latitude (optional, can be set to default if not available)
    private Double startLongitude; // Starting longitude (optional, can be set to default if not available)

    // Default constructor
    public TripRequest() {}

    // Constructor with necessary fields
    public TripRequest(Long driverId, Long patientId, Double startLatitude, Double startLongitude) {
        this.driverId = driverId;
        this.patientId = patientId;
        this.startLatitude = startLatitude;
        this.startLongitude = startLongitude;
    }

    // Getters and setters for all fields
    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Double getStartLatitude() {
        return startLatitude;
    }

    public void setStartLatitude(Double startLatitude) {
        this.startLatitude = startLatitude;
    }

    public Double getStartLongitude() {
        return startLongitude;
    }

    public void setStartLongitude(Double startLongitude) {
        this.startLongitude = startLongitude;
    }
}
