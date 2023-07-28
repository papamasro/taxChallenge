package com.example.demo.service;

import com.example.demo.exception.NoTaxValueException;
import com.example.demo.model.external.ChargesRequest;
import com.example.demo.model.external.ChargesResponse;
import com.example.demo.repository.HistoryRepository;
import com.example.demo.service.external.ExternalMockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import util.DateFormatter;

@Service
public class CalculatorService {

    @Autowired
    ExternalMockService externalMockService;

    @Autowired
    HistoryService historyService;

    @Autowired
    HistoryRepository historyRepository;


    @Cacheable(value = "taxes")
    public Double getTaxes(){
        try{


            Double tax = externalMockService.getTax().getBody(); //TODO REINTENTO 3 VECES
            historyService.saveCallHistory("getTax",200,tax.toString());
            return tax;
        } catch (Exception ex){
            historyService.saveCallHistory("getTax",ex.hashCode(), ex.getMessage().toString());
            throw new NoTaxValueException("No value of taxes available"); //TODO ERROR
        }
    }

     public ChargesResponse calculateTax(ChargesRequest chargesRequest){ //TODO: revisar si se puede hacerm ejor
        Double tax = getTaxes();
        Double resultAddNumbers = chargesRequest.getFirst() + chargesRequest.getSecond();
        Double charges = (resultAddNumbers * tax);
        Double resultWithCharges = resultAddNumbers + charges;
         DateFormatter date = new DateFormatter();
         String dateString = date.getDate();
         ChargesResponse response = new ChargesResponse(dateString,tax,resultWithCharges);
         historyService.saveCallHistory("calculateTax",200,response.toString());
        return response;
    }


}
