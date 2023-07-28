package com.example.demo.controller;

import com.example.demo.model.CalculateTaxRequest;
import com.example.demo.model.CalculateTaxResponse;
import com.example.demo.service.CalculatorService;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/")
public class CalculatorController {

    @Autowired
    CalculatorService calculatorService;

    @PostMapping("calculateTax")
    @ResponseBody
    public ResponseEntity<CalculateTaxResponse> calculateTax(@RequestBody @Nonnull CalculateTaxRequest calculateTaxRequest) {
        CalculateTaxResponse calculated = calculatorService.calculateTax(calculateTaxRequest);
        CalculateTaxResponse taxResponse = new CalculateTaxResponse(calculated.getDate(), calculated.getTax(), 1.0); //TODO ARREGLAR
        return ResponseEntity.ok(taxResponse);
    }

}
