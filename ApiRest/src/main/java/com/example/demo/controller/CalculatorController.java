package com.example.demo.controller;

import com.example.demo.exception.TooManyRequestException;
import com.example.demo.model.services.calculate.CalculateTaxRequest;
import com.example.demo.model.services.calculate.CalculateTaxResponse;
import com.example.demo.model.services.calculate.TaxValueRequest;
import com.example.demo.service.impl.CalculatorService;
import com.example.demo.service.impl.api.RateLimiterServices;
import io.github.bucket4j.Bucket;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@CrossOrigin
@RestController
@RequestMapping("/api/")
@Validated
public class CalculatorController {

    @Autowired
    RateLimiterServices rateLimiter;

    @Autowired
    CalculatorService calculatorService;

    @PostMapping("calculateTax")
    @ResponseBody
    public ResponseEntity<CalculateTaxResponse> calculateTax(@RequestHeader("userId") Integer userId, @Valid @RequestBody CalculateTaxRequest calculateTaxRequest ) {
        Bucket bucket = rateLimiter.resolveBucket(userId.toString());
        if(bucket.tryConsume(1))
        {
            TaxValueRequest taxValueRequest = new TaxValueRequest("IIGG", calculateTaxRequest.getFirst(), calculateTaxRequest.getSecond()); //EXTERNAL SERVICE WILL NEED A TAX NAME?
            CalculateTaxResponse calculated = calculatorService.calculateTax(taxValueRequest);
            return ResponseEntity.ok(calculated);
        }else {
            throw new TooManyRequestException("just three request per minutes are allowed");
        }

    }

}
