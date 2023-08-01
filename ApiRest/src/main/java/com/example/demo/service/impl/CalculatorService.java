package com.example.demo.service.impl;

import com.example.demo.exception.NoTaxValueException;
import com.example.demo.model.external.TaxesServiceRequest;
import com.example.demo.model.external.TaxesServiceResponse;
import com.example.demo.model.jpa.TaxesPercentCache;
import com.example.demo.model.services.calculate.CalculateTaxResponse;
import com.example.demo.model.services.calculate.TaxValueRequest;
import com.example.demo.service.Calculator;
import com.example.demo.service.impl.api.LoggingEventService;
import com.example.demo.service.impl.api.TaxesCacheService;
import com.example.demo.util.DateFormatter;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class CalculatorService implements Calculator {

    private static final Logger logger = LoggerFactory.getLogger(CalculatorService.class);

    Gson gson = new Gson();

    @Autowired
    LoggingEventService loggingEventService;

    @Autowired
    TaxPercentService taxRepository;

    @Autowired
    TaxesCacheService taxesCacheService;

    public BigDecimal getExternalTaxes(String name) {
        try {
            TaxesServiceRequest taxesServiceRequest = new TaxesServiceRequest(name);
            TaxesServiceResponse responseCharge = taxRepository.getTaxes(taxesServiceRequest); //call external service

            CompletableFuture.runAsync(() -> {
                loggingEventService.saveCallHistory("getExternalTax", 200, gson.toJson(responseCharge));
            });

            logger.info("saving taxes in cache");
            TaxesPercentCache taxesPercentCache = new TaxesPercentCache(responseCharge.getName(), responseCharge.getTimestamp(), responseCharge.getTax());
            CompletableFuture.runAsync(() -> {
                taxesCacheService.saveTaxesCache(taxesPercentCache); //TODO if fail redis, the service will not work because this, this is a posible solution (may not the best) same problem in catch
            });
            return responseCharge.getTax();
        } catch (Exception ex) {

            logger.error("error getting taxes from service");
            CompletableFuture.runAsync(() -> {
                loggingEventService.saveCallHistory("getExternalTax", 0, ex.getMessage()); //TODO RESOLVE STATUS CODE
            });
            logger.info("trying to get taxes from cache");
            Optional<TaxesPercentCache> percentageFromCache = taxesCacheService.getLastTaxesFromCache(name); //TODO if fail redis, the service will not work because this, posible solution is adding completabler future with then
            if (percentageFromCache.isPresent()) {
                logger.info("success getting taxes from cache");
                return percentageFromCache.get().getValue();
            } else {
                logger.error("FAILED: No value of taxes available");
                throw new NoTaxValueException("Error: No value of taxes available,try again in a few minutes");
            }
        }
    }

    public CalculateTaxResponse calculateTax(TaxValueRequest chargesRequest) {
        BigDecimal externalTaxes = getExternalTaxes(chargesRequest.getTaxesName());
        BigDecimal resultAddNumbers = chargesRequest.getFirstNumber().add(chargesRequest.getSecondNumber());
        BigDecimal charges = (resultAddNumbers.multiply(externalTaxes));
        BigDecimal resultWithCharges = resultAddNumbers.add(charges);
        Gson gson = new Gson();
        CalculateTaxResponse calculateTaxResponse = new CalculateTaxResponse(DateFormatter.getStringDate(), externalTaxes, resultWithCharges);
        CompletableFuture.runAsync(() -> {
            loggingEventService.saveCallHistory("calculateTax", 200, gson.toJson(calculateTaxResponse));
        });
        return calculateTaxResponse;
    }
}
