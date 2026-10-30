package com.mumbai.metro.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "journeys")
public class Journey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long journeyId;

    @Column(nullable = false)
    private String source;

    @Column(nullable = false)
    private String destination;

    private String transportMode;

    private Integer estimatedMinutes;

    private Double estimatedFare;

    public Journey() {
    }

    public Journey(String source,
            String destination,
            String transportMode,
            Integer estimatedMinutes,
            Double estimatedFare) {

        this.source = source;
        this.destination = destination;
        this.transportMode = transportMode;
        this.estimatedMinutes = estimatedMinutes;
        this.estimatedFare = estimatedFare;
    }

    public Long getJourneyId() {
        return journeyId;
    }

    public void setJourneyId(Long journeyId) {
        this.journeyId = journeyId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getTransportMode() {
        return transportMode;
    }

    public void setTransportMode(String transportMode) {
        this.transportMode = transportMode;
    }

    public Integer getEstimatedMinutes() {
        return estimatedMinutes;
    }

    public void setEstimatedMinutes(Integer estimatedMinutes) {
        this.estimatedMinutes = estimatedMinutes;
    }

    public Double getEstimatedFare() {
        return estimatedFare;
    }

    public void setEstimatedFare(Double estimatedFare) {
        this.estimatedFare = estimatedFare;
    }
}