package com.vkara.property_portfolio_api.service;

import com.vkara.property_portfolio_api.exception.ResourceNotFoundException;
import com.vkara.property_portfolio_api.model.Property;
import com.vkara.property_portfolio_api.model.Unit;
import com.vkara.property_portfolio_api.repository.UnitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitService {

    private final UnitRepository unitRepository;
    private final PropertyService propertyService;

    public UnitService(UnitRepository unitRepository, PropertyService propertyService) {
        this.unitRepository = unitRepository;
        this.propertyService = propertyService;
    }

    public List<Unit> getAllUnits() {
        return unitRepository.findAll();
    }

    public Unit getUnitById(Long id) {
        return unitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unit not found with id: " + id));
    }

    public Unit createUnit(Long propertyId, Unit unit) {
        Property property = propertyService.getPropertyById(propertyId);
        unit.setProperty(property);
        return unitRepository.save(unit);
    }

    public Unit updateUnit(Long id, Unit updatedUnit) {
        Unit existing = getUnitById(id);
        existing.setUnitNumber(updatedUnit.getUnitNumber());
        existing.setMonthlyRent(updatedUnit.getMonthlyRent());
        existing.setOccupied(updatedUnit.getOccupied());
        return unitRepository.save(existing);
    }
    
    public void deleteUnit(Long id) {
        Unit existing = getUnitById(id);
        unitRepository.delete(existing);
    }
}
