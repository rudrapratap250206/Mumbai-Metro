package com.mumbai.metro.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "metro_lines")
public class MetroLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lineId;

    @Column(nullable = false, unique = true)
    private String lineCode;

    @Column(nullable = false)
    private String lineName;

    private String color;

    private String operator;

    private Boolean active;

    public MetroLine() {
    }

    public MetroLine(String lineCode, String lineName, String color,
            String operator, Boolean active) {
        this.lineCode = lineCode;
        this.lineName = lineName;
        this.color = color;
        this.operator = operator;
        this.active = active;
    }

    public Long getLineId() {
        return lineId;
    }

    public void setLineId(Long lineId) {
        this.lineId = lineId;
    }

    public String getLineCode() {
        return lineCode;
    }

    public void setLineCode(String lineCode) {
        this.lineCode = lineCode;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}