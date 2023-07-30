package com.example.demo.controller;

import com.example.demo.model.CalculateTaxRequest;
import com.example.demo.model.CalculateTaxResponse;
import com.example.demo.model.external.TaxValueRequest;
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
        TaxValueRequest taxValueRequest = new TaxValueRequest("IIGG",calculateTaxRequest.getFirst(),calculateTaxRequest.getSecond()); //SERVICE WILL NEED A TAX NAME?
        CalculateTaxResponse calculated = calculatorService.calculateTax(taxValueRequest);

        //MAP RESPONSE
        return ResponseEntity.ok(calculated);
    }

}
