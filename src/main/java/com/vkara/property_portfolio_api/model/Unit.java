package com.vkara.property_portfolio_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table (name = "units")
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "unit number is required")
    private String unitNumber;

    @PositiveOrZero(message = "Monthly rent cannot be negative")
    private Double monthlyRent;

    private Boolean occupied = false;

    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;

    // Constructors
    public Unit() {

    }

    public Unit(String unitNumber, Double monthlyRent, Boolean occupied, Property property) {
        this.unitNumber = unitNumber;
        this.monthlyRent = monthlyRent;
        this.occupied = occupied;
        this.property = property;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    public Double getMonthlyRent() {
        return monthlyRent;
    }

    public void setMonthlyRent(Double monthlyRent) {
        this.monthlyRent = monthlyRent;
    }

    public Boolean getOccupied() {
        return occupied;
    }

    public void setOccupied(Boolean occupied) {
        this.occupied = occupied;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
    
}
