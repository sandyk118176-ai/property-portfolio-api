package com.vkara.property_portfolio_api.service;

import com.vkara.property_portfolio_api.dto.PortfolioSummary;
import com.vkara.property_portfolio_api.model.Unit;
import com.vkara.property_portfolio_api.repository.PropertyRepository;
import com.vkara.property_portfolio_api.repository.UnitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortfolioSummaryService {

    private final PropertyRepository propertyRepository;
    private final UnitRepository unitRepository;

    public PortfolioSummaryService(PropertyRepository propertyRepository, UnitRepository unitRepository) {
        this.propertyRepository = propertyRepository;
        this.unitRepository = unitRepository;
    }

    public PortfolioSummary getSummary() {
        long totalProperties = propertyRepository.count();

        List<Unit> allUnits = unitRepository.findAll();
        long totalUnits = allUnits.size();

        long occupiedUnits = allUnits.stream()
                .filter(Unit::getOccupied)
                .count();

        double occupancyRate = totalUnits == 0
                ? 0.0
                : (double) occupiedUnits / totalUnits * 100;

        double totalMonthlyRent = allUnits.stream()
                .filter(Unit::getOccupied)
                .mapToDouble(Unit::getMonthlyRent)
                .sum();

        return new PortfolioSummary(totalProperties, totalUnits, occupiedUnits, occupancyRate, totalMonthlyRent);

    }
    
}
