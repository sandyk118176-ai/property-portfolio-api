package com.vkara.property_portfolio_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Table(name = "tenants")
public class Tenant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tenant name is required")
    private String name;

    private LocalDate leaseStart;

    private LocalDate leaseEnd;

    @OneToOne
    @JoinColumn(name = "unit_id", nullable = false)
    private Unit unit;

    // Constructors
    public Tenant() {

    }

    public Tenant(String name, LocalDate leaseStart, LocalDate leaseEnd, Unit unit) {
        this.name = name;
        this.leaseStart = leaseStart;
        this.leaseEnd = leaseEnd;
        this.unit = unit;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getLeaseStart() {
        return leaseStart;
    }

    public void setLeaseStart(LocalDate leaseStart) {
        this.leaseStart = leaseStart;
    }

    public LocalDate getLeaseEnd() {
        return leaseEnd;
    }

    public void setLeaseEnd(LocalDate leaseEnd) {
        this.leaseEnd = leaseEnd;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
    
}
