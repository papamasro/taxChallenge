package com.example.demo.service;

import com.example.demo.exception.NoTaxValueException;
import com.example.demo.model.CalculateTaxRequest;
import com.example.demo.model.CalculateTaxResponse;
import com.example.demo.model.external.ChargeRequest;
import com.example.demo.model.external.ChargeResponse;
import com.example.demo.service.api.LoggingEventService;
import com.example.demo.service.api.PercentageCacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.example.demo.util.DateFormatter;

import java.util.Optional;

@Service
public class CalculatorService {

    private static final Logger logger = LoggerFactory.getLogger(CalculatorService.class);

    @Autowired
    LoggingEventService loggingEventService;

    @Autowired
    TaxService taxRepository;

    @Autowired
    PercentageCacheService percentageCacheService;

    @Cacheable(value = "taxes")
    public Double getTaxes() {
        try {
            ChargeRequest requestCharge = new ChargeRequest("taxesName"); //Service need a name?
            ChargeResponse responseCharge = taxRepository.getTaxes(requestCharge);
            loggingEventService.saveCallHistory("getTax", 200, responseCharge.getTax().toString());
            return responseCharge.getTax();
        } catch (Exception ex) {
            logger.error("error getting taxes from service");
            loggingEventService.saveCallHistory("getTax", ex.hashCode(), ex.getMessage().toString());
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
        String date = new DateFormatter().getDate();
        CalculateTaxResponse response = new CalculateTaxResponse(date, tax, resultWithCharges);
        loggingEventService.saveCallHistory("calculateTax", 200, response.toString());
        return response;
    }

    private Optional<Double> getPercentageFromCache() {
        return this.percentageCacheService.getPercentage();
    }

}
