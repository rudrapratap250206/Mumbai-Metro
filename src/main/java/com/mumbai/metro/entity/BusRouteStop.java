package com.mumbai.metro.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "bus_route_stops", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "route_id", "stop_id" })
})
public class BusRouteStop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "route_id", nullable = false)
    private BusRoute busRoute;

    @ManyToOne
    @JoinColumn(name = "stop_id", nullable = false)
    private BusStop busStop;

    @Column(nullable = false)
    private Integer stopSequence;

    public BusRouteStop() {
    }

    public BusRouteStop(BusRoute busRoute, BusStop busStop, Integer stopSequence) {
        this.busRoute = busRoute;
        this.busStop = busStop;
        this.stopSequence = stopSequence;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BusRoute getBusRoute() {
        return busRoute;
    }

    public void setBusRoute(BusRoute busRoute) {
        this.busRoute = busRoute;
    }

    public BusStop getBusStop() {
        return busStop;
    }

    public void setBusStop(BusStop busStop) {
        this.busStop = busStop;
    }

    public Integer getStopSequence() {
        return stopSequence;
    }

    public void setStopSequence(Integer stopSequence) {
        this.stopSequence = stopSequence;
    }
}