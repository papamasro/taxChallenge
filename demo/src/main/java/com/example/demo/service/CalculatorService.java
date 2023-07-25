package com.example.demo.service;

import com.example.demo.exception.NoTaxValueException;
import com.example.demo.model.database.jpa.Taxes;
import com.example.demo.model.database.redis.RedisTaxes;
import com.example.demo.model.external.ChargesRequest;
import com.example.demo.repository.TaxCacheRepository;
import com.example.demo.repository.TaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CalculatorService {

    @Autowired
    private TaxCacheRepository taxCacheRepository;

    @Autowired
    private TaxRepository taxRepository;

    @Autowired
    ExternalMockService externalMockService;
     public Double calculateTax(ChargesRequest chargesRequest){ //TODO: revisar si se puede hacerm ejor
        Double tax = null;
        String endpoint;

            Optional<RedisTaxes> optionalCacheDataTax = taxCacheRepository.findById("tax");
            if (optionalCacheDataTax.isPresent()) {
                String lastTaxValue = optionalCacheDataTax.get().toString();
                tax = Double.valueOf(lastTaxValue);
                endpoint = "cache";
            }else{
                try{
                    tax = externalMockService.getTax();
                    endpoint = "service";
                } catch (Exception ex){
                    saveTaxHistory("failed",408, ex.getMessage()); //IMPRMIR ERROR TODO
                    throw new NoTaxValueException("No value of taxes available"); //TODO ERROR
                }
        }
        saveRedisTaxHistory(endpoint,200,tax.toString()); //TODO ESTO SE HACE ASI?
        saveTaxHistory(endpoint,200,tax.toString()); //TODO SE SALVA EN BD? VA A TARDAR TIEMPO
        Double resultWithCharges = null;
        Double resultAddNumbers = chargesRequest.getFirst() + chargesRequest.getSecond();
        Double charges = resultAddNumbers * tax;
        resultWithCharges = resultAddNumbers + charges;
        return resultWithCharges;
    }
    public void saveTaxHistory(String endpoint, int statusCode, String response) {
        Taxes callHistory = new Taxes();
        callHistory.setTimestamp(LocalDateTime.now().toString());
        callHistory.setEndpoint(endpoint);
        callHistory.setStatusCode(statusCode);
        callHistory.setResponse(response);
        taxRepository.save(callHistory);
    }

    public void saveRedisTaxHistory(String endpoint, int statusCode, String response) {
        RedisTaxes callHistory = new RedisTaxes();
        callHistory.setTimestamp(LocalDateTime.now().toString());
        callHistory.setEndpoint(endpoint);
        callHistory.setStatusCode(statusCode);
        callHistory.setResponse(response);
        taxCacheRepository.save(callHistory);
    }

}
