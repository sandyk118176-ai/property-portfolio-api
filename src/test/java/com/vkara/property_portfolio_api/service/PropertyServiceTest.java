package com.vkara.property_portfolio_api.service;

import com.vkara.property_portfolio_api.exception.ResourceNotFoundException;
import com.vkara.property_portfolio_api.model.Property;
import com.vkara.property_portfolio_api.repository.PropertyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PropertyServiceTest {

    @Mock
    private PropertyRepository propertyRepository;

    @InjectMocks
    private PropertyService propertyService;

    private Property testProperty;

    @BeforeEach
    void setUp() {
        testProperty = new Property("123 Main St", 250000.0, 1200.0);
        testProperty.setId(1L);
    }

    @Test
    void getPropertyById_returnsProperty_whenPropertyExists() {
        when(propertyRepository.findById(1L)).thenReturn(Optional.of(testProperty));

        Property result = propertyService.getPropertyById(1L);

        assertEquals("123 Main St", result.getAddress());
        assertEquals(1L, result.getId());
    }

    @Test
    void getPropertyById_throwsException_whenPropertyDoesNotExist() {
        when(propertyRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            propertyService.getPropertyById(99L);
        });
    }

    @Test
    void createProperty_savesAndReturnsProperty() {
        when(propertyRepository.save(any(Property.class))).thenReturn(testProperty);

        Property result = propertyService.createProperty(testProperty);

        assertEquals("123 Main St", result.getAddress());
        verify(propertyRepository, times(1)).save(testProperty);
    }

    @Test
    void getAllProperties_returnsListOfProperties() {
        Property secondProperty = new Property("456 Oak Ave", 320000.0, 1450.0);
        secondProperty.setId(2L);

        when(propertyRepository.findAll()).thenReturn(List.of(testProperty, secondProperty));

        List<Property> results = propertyService.getAllProperties();

        assertEquals(2, results.size());
    }

    @Test
    void deleteProperty_callRepositoryDelete() {
        when(propertyRepository.findById(1L)).thenReturn(Optional.of(testProperty));

        propertyService.deleteProperty(1L);

        verify(propertyRepository, times(1)).delete(testProperty);
    }
    
}
