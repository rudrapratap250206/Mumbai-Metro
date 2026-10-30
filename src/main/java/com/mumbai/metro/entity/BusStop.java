package com.mumbai.metro.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "bus_stops")
public class BusStop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stopId;

    @Column(nullable = false, unique = true)
    private String stopCode;

    @Column(nullable = false)
    private String stopName;

    private String city;

    private Double latitude;

    private Double longitude;

    private Boolean active;

    public BusStop() {
    }

    public BusStop(String stopCode, String stopName, String city,
            Double latitude, Double longitude, Boolean active) {
        this.stopCode = stopCode;
        this.stopName = stopName;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.active = active;
    }

    public Long getStopId() {
        return stopId;
    }

    public void setStopId(Long stopId) {
        this.stopId = stopId;
    }

    public String getStopCode() {
        return stopCode;
    }

    public void setStopCode(String stopCode) {
        this.stopCode = stopCode;
    }

    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}