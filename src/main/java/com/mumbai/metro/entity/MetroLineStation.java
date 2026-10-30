package com.mumbai.metro.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "metro_line_stations", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "line_id", "station_id" })
})
public class MetroLineStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "line_id", nullable = false)
    private MetroLine metroLine;

    @ManyToOne
    @JoinColumn(name = "station_id", nullable = false)
    private Station station;

    @Column(nullable = false)
    private Integer stationSequence;

    public MetroLineStation() {
    }

    public MetroLineStation(MetroLine metroLine, Station station,
            Integer stationSequence) {
        this.metroLine = metroLine;
        this.station = station;
        this.stationSequence = stationSequence;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MetroLine getMetroLine() {
        return metroLine;
    }

    public void setMetroLine(MetroLine metroLine) {
        this.metroLine = metroLine;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Integer getStationSequence() {
        return stationSequence;
    }

    public void setStationSequence(Integer stationSequence) {
        this.stationSequence = stationSequence;
    }
}