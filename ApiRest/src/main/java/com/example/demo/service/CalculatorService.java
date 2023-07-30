package com.example.demo.service;

import com.example.demo.exception.NoTaxValueException;
import com.example.demo.model.CalculateTaxResponse;
import com.example.demo.model.external.TaxValueRequest;
import com.example.demo.model.external.TaxValueResponse;
import com.example.demo.model.external.TaxesServiceRequest;
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
            TaxValueResponse responseCharge = taxRepository.getTaxes(taxesServiceRequest); //call external service

            CompletableFuture.runAsync(() -> {
                loggingEventService.saveCallHistory("getExternalTax", 200, gson.toJson(responseCharge));
                logger.info("success saving history call on BD on other thread");
            });

            logger.info("saving taxes in cache");
            TaxesCache taxesCache = new TaxesCache(responseCharge.getName(), responseCharge.getTimestamp(), responseCharge.getTax());
            taxesCacheService.saveTaxesCache(taxesCache);

            return responseCharge.getTax();
        } catch (Exception ex) {

            logger.error("error getting taxes from service");
            CompletableFuture.runAsync(() -> {
                loggingEventService.saveCallHistory("getExternalTax", 404, ex.getMessage()); //TODO RESOLVE STATUS CODE
                logger.info("failed tax service, but success saving history call on BD");
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
        String date = new DateFormatter().getStringDate();
        CalculateTaxResponse response = new CalculateTaxResponse(date, externalTaxes, resultWithCharges);
        CompletableFuture.runAsync(() -> {
            loggingEventService.saveCallHistory("calculateTax", 200, gson.toJson(response));
            logger.info("success saving history call on BD");
        });
        return response;
    }
}
