package com.mumbai.metro.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "stations")
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stationId;

    @Column(nullable = false, unique = true)
    private String stationCode;

    @Column(nullable = false)
    private String stationName;

    private String city;

    private Double latitude;

    private Double longitude;

    private Boolean interchange;

    private Boolean active;

    public Station() {
    }

    public Station(String stationCode, String stationName, String city,
            Double latitude, Double longitude,
            Boolean interchange, Boolean active) {
        this.stationCode = stationCode;
        this.stationName = stationName;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.interchange = interchange;
        this.active = active;
    }

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
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

    public Boolean getInterchange() {
        return interchange;
    }

    public void setInterchange(Boolean interchange) {
        this.interchange = interchange;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}