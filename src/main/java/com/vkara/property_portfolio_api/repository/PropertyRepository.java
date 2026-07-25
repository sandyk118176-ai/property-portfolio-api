package com.vkara.property_portfolio_api.repository;

import com.vkara.property_portfolio_api.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PropertyRepository extends JpaRepository <Property, Long> {

    
}

