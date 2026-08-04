package com.vkara.property_portfolio_api.service;

import com.vkara.property_portfolio_api.exception.ResourceNotFoundException;
import com.vkara.property_portfolio_api.model.Property;
import com.vkara.property_portfolio_api.model.Unit;
import com.vkara.property_portfolio_api.repository.UnitRepository;
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
class UnitServiceTest {
    
    @Mock
    private UnitRepository unitRepository;

    @Mock
    private PropertyService propertyService;

    @InjectMocks
    private UnitService unitService;

    private Property testProperty;
    private Unit testUnit;

    @BeforeEach
    void setUp() {
        testProperty = new Property("123 Main St", 250000.0, 1200.0);
        testProperty.setId(1L);

        testUnit= new Unit("A1", 1500.0, false, testProperty);
        testUnit.setId(1L);
    }

    @Test
    void createUnit_setPropertyAndSaves() {
        when(propertyService.getPropertyById(1L)).thenReturn(testProperty);
        when(unitRepository.save(any(Unit.class))).thenReturn(testUnit);

        Unit newUnit = new Unit("A1", 1500.0, false, null);
        Unit result = unitService.createUnit(1L, newUnit);

        assertEquals(testProperty, newUnit.getProperty());
        assertEquals("A1", result.getUnitNumber());
        verify(unitRepository, times(1)).save(newUnit);
    }

    @Test
    void getUnitById_throwsException_whenUnitDoesNotExist() {
        when(unitRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            unitService.getUnitById(99L);
        });
    }

    @Test
    void updateUnit_updatesFieldsCorrectly() {
        when(unitRepository.findById(1L)).thenReturn(Optional.of(testUnit));
        when(unitRepository.save(any(Unit.class))).thenReturn(testUnit);

        Unit updatedData = new Unit("A2", 1600.0, true, null);
        Unit result = unitService.updateUnit(1L, updatedData);

        assertEquals("A2", result.getUnitNumber());
        assertEquals(1600.0, result.getMonthlyRent());
        assertTrue(result.getOccupied());
    }

    @Test
    void getAllUnits_returnsListOfUnits() {
        when(unitRepository.findAll()).thenReturn(List.of(testUnit));

        List<Unit> results = unitService.getAllUnits();

        assertEquals(1, results.size());
        assertEquals("A1", results.get(0).getUnitNumber());
    }

}
