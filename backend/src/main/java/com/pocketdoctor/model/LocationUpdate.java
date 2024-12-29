package com.pocketdoctor.model;

public class LocationUpdate {

    private Double latitude; // Current latitude of the ambulance
    private Double longitude; // Current longitude of the ambulance

    // Default constructor
    public LocationUpdate() {}

    // Constructor with latitude and longitude
    public LocationUpdate(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getters and setters for latitude and longitude
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
}
