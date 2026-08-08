package com.vkara.property_portfolio_api.controller;

import com.vkara.property_portfolio_api.dto.PortfolioSummary;
import com.vkara.property_portfolio_api.service.PortfolioSummaryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/summary")
public class PortfolioSummaryController {
    private final PortfolioSummaryService portfolioSummaryService;

    public PortfolioSummaryController(PortfolioSummaryService portfolioSummaryService) {
        this.portfolioSummaryService = portfolioSummaryService;
    }

    @GetMapping
    public PortfolioSummary getSummary() {
        return portfolioSummaryService.getSummary();
    }
    
}
