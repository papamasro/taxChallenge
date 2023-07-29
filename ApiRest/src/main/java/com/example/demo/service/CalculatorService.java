package com.example.demo.service;

import com.example.demo.exception.NoTaxValueException;
import com.example.demo.model.CalculateTaxRequest;
import com.example.demo.model.CalculateTaxResponse;
import com.example.demo.model.external.TaxValueRequest;
import com.example.demo.model.external.TaxValueResponse;
import com.example.demo.service.api.LoggingEventService;
import com.example.demo.service.api.PercentageCacheService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.util.DateFormatter;

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
    PercentageCacheService percentageCacheService;

    //@Cacheable(value = "taxes")
    public Double getTaxes() {
        try {
            TaxValueRequest requestCharge = new TaxValueRequest("taxesName"); //Service need a name?
            TaxValueResponse responseCharge = taxRepository.getTaxes(requestCharge);
            CompletableFuture.supplyAsync(() -> { //TODO REVISAR SI ESTA BIEN IMPLEMENTADO
                loggingEventService.saveCallHistory("getTax", 200, gson.toJson(responseCharge));
                return 200;
            }).thenApply(s -> {
                logger.info("success saving history call on BD");
                return s;
            });
            logger.info("saving taxes in cache");
    //        savePercentageFromCache(responseCharge.getTax()); //TODO NO SAVING
            return responseCharge.getTax();
        } catch (Exception ex) {
            logger.error("error getting taxes from service");

            CompletableFuture.supplyAsync(() -> { //TODO REVISAR SI ESTA BIEN IMPLEMENTADO
                loggingEventService.saveCallHistory("getTax", ex.hashCode(), ex.getMessage().toString());
                return 200;
            }).thenApply(s -> {
                logger.info("success saving history call on BD");
                return s;
            });

            logger.info("getting taxes from cache");
            Optional<Double> percentageFromCache = this.getPercentageFromCache();
            if(percentageFromCache.isPresent()) {
                logger.info("success getting taxes from cache");
                return percentageFromCache.get();
            }else{
                throw new NoTaxValueException("No value of taxes available"); //TODO ERROR
            }
        }
    }

    public CalculateTaxResponse calculateTax(CalculateTaxRequest chargesRequest) { //TODO: revisar si se puede hacerm ejor
        Double tax = getTaxes();
        Double resultAddNumbers = chargesRequest.getFirst() + chargesRequest.getSecond();
        Double charges = (resultAddNumbers * tax);
        Double resultWithCharges = resultAddNumbers + charges;
        String date = new DateFormatter().getStringDate();
        CalculateTaxResponse response = new CalculateTaxResponse(date, tax, resultWithCharges);
        CompletableFuture.supplyAsync(() -> { //TODO REVISAR SI ESTA BIEN IMPLEMENTADO
            loggingEventService.saveCallHistory("calculateTax", 200, gson.toJson(response));
            return 200;
        }).thenApply(s -> {
            logger.info("success saving history call on BD");
            return s;
        });
        return response;
    }

    Optional<Double> getPercentageFromCache() {
        return this.percentageCacheService.getPercentage();
    }

    void savePercentageFromCache(Double taxes) {
        this.percentageCacheService.savePercentage(taxes);
    }

}
