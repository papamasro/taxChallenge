package com.example.demo.controller;

import com.example.demo.model.CalculateTaxRequest;
import com.example.demo.model.CalculateTaxResponse;
import com.example.demo.service.CalculatorService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/")
@RateLimiter(name = "basic")
public class CalculatorController {

    @Autowired
    CalculatorService calculatorService;

    @PostMapping("calculateTax")
    @ResponseBody
    public ResponseEntity<CalculateTaxResponse> calculateTax(@RequestBody @Nonnull CalculateTaxRequest calculateTaxRequest) {
        CalculateTaxResponse calculated = calculatorService.calculateTax(calculateTaxRequest);
        return ResponseEntity.ok(calculated);
    }

}
