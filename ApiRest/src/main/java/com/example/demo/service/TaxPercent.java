package com.example.demo.service;

import com.example.demo.model.external.TaxesServiceRequest;
import com.example.demo.model.external.TaxesServiceResponse;

public interface TaxPercent {


    TaxesServiceResponse getTaxes(TaxesServiceRequest request);
}
