package com.example.controller;

import com.example.model.UserData;
import com.example.service.AggregationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AggregationController {
    private final AggregationService aggregationService;

    public AggregationController(AggregationService aggregationService) {
        this.aggregationService = aggregationService;
    }

    @GetMapping("/dashboard")
    public ResponseEntity<UserData> getDashboard() {
        return ResponseEntity.ok(aggregationService.getAggregatedData());
    }
}
