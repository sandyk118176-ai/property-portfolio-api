package com.vkara.property_portfolio_api.controller;

import com.vkara.property_portfolio_api.model.Unit;
import com.vkara.property_portfolio_api.service.UnitService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UnitController {

    private final UnitService unitService;

    public UnitController(UnitService unitService) {
        this.unitService = unitService;
    }

    @GetMapping("/units")
    public List<Unit> getAllUnits() {
        return unitService.getAllUnits();
    }

    @GetMapping("/units/{id}")
    public Unit getUnitById(@PathVariable Long id) {
        return unitService.getUnitById(id);
    }

    @PostMapping("/properties/{propertyId}/units")
    public Unit createUnit(@PathVariable Long propertyId, @RequestBody Unit unit) {
        return unitService.createUnit(propertyId, unit);
    }

    @PutMapping("/units/{id}")
    public Unit updateUnit(@PathVariable Long id, @RequestBody Unit unit) {
        return unitService.createUnit(id, unit);
    }

    @DeleteMapping("/units/{id}")
    public void deleteUnit(@PathVariable Long id) {
        unitService.deleteUnit(id);
    }
    
}
