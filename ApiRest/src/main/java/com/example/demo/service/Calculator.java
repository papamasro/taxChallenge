package com.example.demo.service;

import com.example.demo.model.services.calculate.CalculateTaxResponse;
import com.example.demo.model.services.calculate.TaxValueRequest;

import java.math.BigDecimal;

public interface Calculator {

    BigDecimal getExternalTaxes(String name);

    CalculateTaxResponse calculateTax(TaxValueRequest chargesRequest);
}
