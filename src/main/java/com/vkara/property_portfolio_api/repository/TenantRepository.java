package com.vkara.property_portfolio_api.repository;

import com.vkara.property_portfolio_api.model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantRepository extends JpaRepository<Tenant, Long> {

}

