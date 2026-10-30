package com.mumbai.metro.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "bus_routes")
public class BusRoute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long routeId;

    @Column(nullable = false, unique = true)
    private String routeNumber;

    @Column(nullable = false)
    private String routeName;

    @Column(name = "operator_name")
    private String operator;

    private String startPoint;

    private String endPoint;

    private Boolean active;

    public BusRoute() {
    }

    public BusRoute(String routeNumber, String routeName,
            String operator, String startPoint,
            String endPoint, Boolean active) {

        this.routeNumber = routeNumber;
        this.routeName = routeName;
        this.operator = operator;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.active = active;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public String getRouteNumber() {
        return routeNumber;
    }

    public void setRouteNumber(String routeNumber) {
        this.routeNumber = routeNumber;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}