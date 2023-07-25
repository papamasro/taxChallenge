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
public class TaxService {

    @Autowired
    private TaxCacheRepository taxCacheRepository;

    @Autowired
    private TaxRepository taxRepository;

    @Autowired
    MockService mockService;
     public Double calculateTax(ChargesRequest chargesRequest){ //TODO: revisar si se puede hacerm ejor
        Double tax = null;
        String endpoint;
        try{
            tax = getPercentTax();
            endpoint = "service";
        } catch (Exception ex){
            Optional<RedisTaxes> optionalCacheDataTax = taxCacheRepository.findById("tax");
            if (optionalCacheDataTax.isPresent()) {
                String lastTaxValue = optionalCacheDataTax.get().toString();
                tax = Double.valueOf(lastTaxValue);
                endpoint = "cache";

            }else{
                saveTaxHistory("failed",408, ex.getMessage()); //IMPRMIR ERROR TODO
                throw new NoTaxValueException("No value of taxes available"); //TODO ERROR
            }
        }
         saveRedisTaxHistory(endpoint,200,tax.toString()); //TODO ESTO SE HACE ASI?
        saveTaxHistory(endpoint,200,tax.toString());
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





    private Double getPercentTax(){
        return mockService.getTax();
    }




/*

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id).get();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

 */
}
