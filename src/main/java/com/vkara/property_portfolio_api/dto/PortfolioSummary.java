package com.vkara.property_portfolio_api.dto;

public class PortfolioSummary {

    private long totalProperties;
    private long totalUnits;
    private long occupiedUnits;
    private double occupancyRate;
    private double totalMonthlyRent;

    public PortfolioSummary(long totalProperties, long totalUnits, long occupiedUnits,
                             double occupancyRate, double totalMonthlyRent) {
                                this.totalProperties = totalProperties;
                                this.totalUnits = totalUnits;
                                this.occupiedUnits = occupiedUnits;
                                this.occupancyRate = occupancyRate;
                                this.totalMonthlyRent = totalMonthlyRent;
                             }

    public long getTotalProperties() {
        return totalProperties;
    }

    public long getTotalUnits() {
        return totalUnits;
    }

    public long getOccupiedUnits() {
        return occupiedUnits;
    }

    public double getOccupancyRate() {
        return occupancyRate;
    }

    public double getTotalMonthlyRent() {
        return totalMonthlyRent;
    }
    
}
