package com.example.demo.service;

import com.example.demo.exception.NoTaxValueException;
import com.example.demo.model.CalculateTaxRequest;
import com.example.demo.model.CalculateTaxResponse;
import com.example.demo.model.external.ChargeRequest;
import com.example.demo.model.external.ChargeResponse;
import com.example.demo.repository.TaxRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import util.DateFormatter;

@Service
public class CalculatorService {

    @Autowired
    LoggingEventService loggingEventService;

    @Autowired
    TaxRespository taxRepository;


    @Cacheable(value = "taxes")
    public Double getTaxes() {
        try {
            ChargeRequest requestCharge = new ChargeRequest("taxesName"); //Service need a name?
            ChargeResponse responseCharge = taxRepository.getTaxes(requestCharge);
            loggingEventService.saveCallHistory("getTax", 200, responseCharge.getTax().toString());
            return responseCharge.getTax();
        } catch (Exception ex) {
            loggingEventService.saveCallHistory("getTax", ex.hashCode(), ex.getMessage().toString());
            throw new NoTaxValueException("No value of taxes available"); //TODO ERROR
        }
    }

    public CalculateTaxResponse calculateTax(CalculateTaxRequest chargesRequest) { //TODO: revisar si se puede hacerm ejor
        Double tax = getTaxes();
        Double resultAddNumbers = chargesRequest.getFirst() + chargesRequest.getSecond();
        Double charges = (resultAddNumbers * tax);
        Double resultWithCharges = resultAddNumbers + charges;
        DateFormatter date = new DateFormatter();
        String dateString = date.getDate();
        CalculateTaxResponse response = new CalculateTaxResponse(dateString, tax, resultWithCharges);
        loggingEventService.saveCallHistory("calculateTax", 200, response.toString());
        return response;
    }


}
