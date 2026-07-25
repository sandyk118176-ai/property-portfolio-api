package com.vkara.property_portfolio_api.service;

import com.vkara.property_portfolio_api.model.Tenant;
import com.vkara.property_portfolio_api.model.Unit;
import com.vkara.property_portfolio_api.repository.TenantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TenantService {
    private final TenantRepository tenantRepository;
    private final UnitService unitService;

    public TenantService(TenantRepository tenantRepository, UnitService unitService) {
        this.tenantRepository = tenantRepository;
        this.unitService = unitService;
    }
    
    public List<Tenant> getAllTenants() {
        return tenantRepository.findAll();
    }

    public Tenant getTenantById(Long id) {
        return tenantRepository.findById(id)
                 .orElseThrow(() -> new RuntimeException("Tenant not found with id: " + id));
    }

    public Tenant createTenant(Long unitId, Tenant tenant) {
        Unit unit = unitService.getUnitById(unitId);
        tenant.setUnit(unit);

        // Business logic: mark the unit as occupied when a tenant moves in
        unit.setOccupied(true);
        unitService.updateUnit(unitId, unit);

        return tenantRepository.save(tenant);
    }

    public Tenant updateTenant(Long id, Tenant updatedTenant) {
        Tenant existing = getTenantById(id);
        existing.setName(updatedTenant.getName());
        existing.setLeaseStart(updatedTenant.getLeaseStart());
        existing.setLeaseEnd(updatedTenant.getLeaseEnd());
        return tenantRepository.save(existing);
    }

    public void deleteTenant(Long id) {
        Tenant existing = getTenantById(id);
        Unit unit = existing.getUnit();
        tenantRepository.delete(existing);

        // Business logic: mark the unit as vacant when a tenant moves out
        unit.setOccupied(false);
        unitService.updateUnit(unit.getId(), unit);
    }
}
