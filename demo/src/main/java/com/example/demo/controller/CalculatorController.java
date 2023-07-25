package com.example.demo.controller;

import com.example.demo.model.CalculateTaxRequest;
import com.example.demo.model.external.ChargesRequest;
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

    @GetMapping("calculateTax")
    @ResponseBody
    public ResponseEntity<Double> calculateTax(@RequestBody @Nonnull CalculateTaxRequest calculateTaxRequest)
    {
        ChargesRequest chargeRequest = new ChargesRequest(calculateTaxRequest.getFirst(),calculateTaxRequest.getSecond());
        Double taxResponse = calculatorService.calculateTax(chargeRequest);
        return ResponseEntity.ok(taxResponse);
    }

}
