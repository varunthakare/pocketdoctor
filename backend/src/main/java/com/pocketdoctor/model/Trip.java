package com.pocketdoctor.model;



import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique identifier for each trip

    @Column(nullable = false)
    private Long driverId; // ID of the driver

    @Column(nullable = false)
    private Long patientId; // ID of the patient being transported

    @Column(nullable = false)
    private String status; // Status of the trip (e.g., "Started", "Completed")

    private Double latitude; // Current latitude of the ambulance

    private Double longitude; // Current longitude of the ambulance

    private String liveLocationUrl; // URL to track live location

    private LocalDateTime createdAt; // Timestamp of when the trip was created

    private LocalDateTime updatedAt; // Timestamp of when the trip was last updated

    // Default constructor
    public Trip() {}

    // Constructor with necessary fields
    public Trip(Long driverId, Long patientId, String status) {
        this.driverId = driverId;
        this.patientId = patientId;
        this.status = status;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Getters and setters for all fields

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getLiveLocationUrl() {
        return liveLocationUrl;
    }

    public void setLiveLocationUrl(String liveLocationUrl) {
        this.liveLocationUrl = liveLocationUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    // Method to update trip's location
    public void updateLocation(Double latitude, Double longitude, String liveLocationUrl) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.liveLocationUrl = liveLocationUrl;
        this.updatedAt = LocalDateTime.now();
    }
}
