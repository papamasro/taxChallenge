package com.example.demo.service;

import com.example.demo.exception.NoTaxValueException;
import com.example.demo.model.services.calculate.CalculateTaxResponse;
import com.example.demo.model.services.calculate.TaxValueRequest;
import com.example.demo.model.external.TaxesServiceRequest;
import com.example.demo.model.external.TaxesServiceResponse;
import com.example.demo.model.jpa.TaxesCache;
import com.example.demo.service.api.LoggingEventService;
import com.example.demo.service.api.TaxesCacheService;
import com.example.demo.util.DateFormatter;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class CalculatorService {

    private static final Logger logger = LoggerFactory.getLogger(CalculatorService.class);

    Gson gson = new Gson();

    @Autowired
    LoggingEventService loggingEventService;

    @Autowired
    TaxService taxRepository;

    @Autowired
    TaxesCacheService taxesCacheService;

    public Double getExternalTaxes(String name) {
        try {
            TaxesServiceRequest taxesServiceRequest = new TaxesServiceRequest(name);
            TaxesServiceResponse responseCharge = taxRepository.getTaxes(taxesServiceRequest); //call external service

            CompletableFuture.runAsync(() -> {
                loggingEventService.saveCallHistory("getExternalTax", 200, gson.toJson(responseCharge));
                logger.info("success saving history call on BD");
            });

            logger.info("saving taxes in cache");
            TaxesCache taxesCache = new TaxesCache(responseCharge.getName(), responseCharge.getTimestamp(), responseCharge.getTax());
            taxesCacheService.saveTaxesCache(taxesCache);

            return responseCharge.getTax();
        } catch (Exception ex) {

            logger.error("error getting taxes from service");
            CompletableFuture.runAsync(() -> {
                loggingEventService.saveCallHistory("getExternalTax", 404, ex.getMessage()); //TODO RESOLVE STATUS CODE
                logger.info("success saving history call on BD");
            });

            logger.info("trying to get taxes from cache");
            Optional<TaxesCache> percentageFromCache = taxesCacheService.getLastTaxesFromCache(name);
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

        Double externalTaxes = getExternalTaxes(chargesRequest.getTaxesName());
        Double resultAddNumbers = chargesRequest.getFirstNumber() + chargesRequest.getSecondNumber();
        Double charges = (resultAddNumbers * externalTaxes);
        Double resultWithCharges = resultAddNumbers + charges;
        Gson gson = new Gson();
        CalculateTaxResponse calculateTaxResponse = new CalculateTaxResponse(DateFormatter.getStringDate(), externalTaxes, resultWithCharges);
        CompletableFuture.runAsync(() -> {
            loggingEventService.saveCallHistory("calculateTax", 200, gson.toJson(calculateTaxResponse));
            logger.info("success saving history call on BD");
        });
        return calculateTaxResponse;
    }
}
