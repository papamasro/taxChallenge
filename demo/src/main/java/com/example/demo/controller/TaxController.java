package com.example.demo.controller;

import com.example.demo.model.CalculateTaxRequest;
import com.example.demo.model.external.ChargesRequest;
import com.example.demo.model.external.ChargesResponse;
import com.example.demo.service.TaxService;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/")
public class TaxController {

@Autowired
TaxService taxService;

    @GetMapping("calculateTax")
    @ResponseBody
    public ResponseEntity<Double> calculateTax(@RequestBody @Nonnull CalculateTaxRequest calculateTaxRequest)
    {
        ChargesRequest chargeRequest = new ChargesRequest(calculateTaxRequest.getFirst(),calculateTaxRequest.getSecond());
        Double taxResponse = taxService.calculateTax(chargeRequest);
        return ResponseEntity.ok(taxResponse);
    }

}
