package com.vkara.property_portfolio_api.controller;

import com.vkara.property_portfolio_api.model.Tenant;
import com.vkara.property_portfolio_api.service.TenantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TenantController {

    private final TenantService tenantService;

    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @GetMapping("/tenants")
    public List<Tenant> getAllTenants() {
        return tenantService.getAllTenants();
    }

    @GetMapping("/tenants/{id}")
    public Tenant getTenantById(@PathVariable Long id) {
        return tenantService.getTenantById(id);
    }

    @PostMapping("/units/{unitId}/tenants")
    public Tenant createTenant(@PathVariable Long unitId, @RequestBody Tenant tenant) {
        return tenantService.createTenant(unitId, tenant);
    }

    @PutMapping("/tenants/{id}") 
    public Tenant updateTenant(@PathVariable Long id, @RequestBody Tenant tenant) {
        return tenantService.updateTenant(id, tenant);
    }

    @DeleteMapping("/tenants/{id}")
    public void deleteTenant(@PathVariable Long id) {
        tenantService.deleteTenant(id);
    }
}
