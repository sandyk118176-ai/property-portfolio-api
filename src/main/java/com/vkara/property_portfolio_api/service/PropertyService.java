package com.vkara.property_portfolio_api.service;

import com.vkara.property_portfolio_api.exception.ResourceNotFoundException;
import com.vkara.property_portfolio_api.model.Property;
import com.vkara.property_portfolio_api.repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {

    
    private final PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    public Property getPropertyById(Long id) {
        return propertyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with id: " + id));
    }

    public Property createProperty(Property property) {
        return propertyRepository.save(property);
    }

    public Property updateProperty(Long id, Property updatedProperty) {
        Property existing = getPropertyById(id);
        existing.setAddress(updatedProperty.getAddress());
        existing.setPurchasePrice(updatedProperty.getPurchasePrice());
        existing.setMonthlyExpenses(updatedProperty.getMonthlyExpenses());
        return propertyRepository.save(existing);
    }

    public void deleteProperty(Long id) {
        Property existing = getPropertyById(id);
        propertyRepository.delete(existing);

    }
    
}
