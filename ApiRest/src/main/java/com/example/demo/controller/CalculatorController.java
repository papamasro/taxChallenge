package com.example.demo.controller;

import com.example.demo.model.services.calculate.CalculateTaxRequest;
import com.example.demo.model.services.calculate.CalculateTaxResponse;
import com.example.demo.model.services.calculate.TaxValueRequest;
import com.example.demo.service.impl.CalculatorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/")
@Validated
public class CalculatorController {

    @Autowired
    CalculatorService calculatorService;

    @PostMapping("calculateTax")
    @ResponseBody
    public ResponseEntity<CalculateTaxResponse> calculateTax(@Valid @RequestBody CalculateTaxRequest calculateTaxRequest) {
        TaxValueRequest taxValueRequest = new TaxValueRequest("IIGG", calculateTaxRequest.getFirst(), calculateTaxRequest.getSecond()); //EXTERNAL SERVICE WILL NEED A TAX NAME?
        CalculateTaxResponse calculated = calculatorService.calculateTax(taxValueRequest);
        return ResponseEntity.ok(calculated);
    }

}
