package com.vkara.property_portfolio_api.service;

import com.vkara.property_portfolio_api.model.Tenant;
import com.vkara.property_portfolio_api.model.Unit;
import com.vkara.property_portfolio_api.repository.TenantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TenantServiceTest {

    @Mock
    private TenantRepository tenantRepository;

    @Mock 
    private UnitService unitService;

    @InjectMocks
    private TenantService tenantService;

    private Unit testUnit;
    private Tenant testTenant;

    @BeforeEach
    void setUp() {
        testUnit = new Unit("A1", 1500.0, false, null);
        testUnit.setId(1L);

        testTenant = new Tenant("Jane Doe", null, null, testUnit);
        testTenant.setId(1L);
    }

    @Test
    void createTenant_markUnitAsOccupied() {
        // Arrange: tell the mocks what to run when called
        when(unitService.getUnitById(1L)).thenReturn(testUnit);
        when(tenantRepository.save(any(Tenant.class))).thenReturn(testTenant);

        // Act: call the real method we're testing
        Tenant result = tenantService.createTenant(1L, testTenant);

        // Assert: verify the outcome
        assertEquals("Jane Doe", result.getName());
        assertTrue(testUnit.getOccupied(), "Unit should be marked occupied after tenant moves in");
        verify(unitService, times(1)).updateUnit(eq(1L), any(Unit.class));
    }

    @Test
    void deleteTenant_markUnitAsVacant() {
        // Arrange
        testUnit.setOccupied(true);
        when(tenantRepository.findById(1L)).thenReturn(java.util.Optional.of(testTenant));

        // Act
        tenantService.deleteTenant(1L);

        // Assert
        assertEquals(false, testUnit.getOccupied(), "Unit should be marked as vacant after the tenant moves out");
        verify(tenantRepository, times(1)).delete(testTenant);
        verify(unitService, times(1)).updateUnit(eq(1L), any(Unit.class));
    }
}
