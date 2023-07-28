package com.example.demo.controller;

import com.example.demo.model.CalculateTaxRequest;
import com.example.demo.model.CalculateTaxResponse;
import com.example.demo.model.external.ChargesRequest;
import com.example.demo.model.external.ChargesResponse;
import com.example.demo.service.CalculatorService;
import com.google.gson.Gson;
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
    public ResponseEntity<CalculateTaxResponse> calculateTax(@RequestBody @Nonnull CalculateTaxRequest calculateTaxRequest)
    {
        ChargesRequest chargeRequest = new ChargesRequest(calculateTaxRequest.getFirst(),calculateTaxRequest.getSecond());
        ChargesResponse calculated = calculatorService.calculateTax(chargeRequest);
        CalculateTaxResponse taxResponse = new CalculateTaxResponse(calculated.getDate(), calculated.getTax(), 1.0); //ARREGLAR
        return ResponseEntity.ok(taxResponse);
    }

}
