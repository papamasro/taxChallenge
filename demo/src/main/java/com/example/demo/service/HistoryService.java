package com.example.demo.service;

import com.example.demo.dto.TaxesDTO;
import com.example.demo.exception.NoTaxValueException;
import com.example.demo.model.database.jpa.Taxes;
import com.example.demo.model.database.redis.RedisTaxes;
import com.example.demo.model.external.ChargesRequest;
import com.example.demo.repository.HistoryRepository;
import com.example.demo.repository.TaxCacheRepository;
import com.example.demo.repository.TaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class HistoryService {

    @Autowired
    private HistoryRepository callHistoryRepository;

    @Autowired
    private TaxRepository taxRepository; //TODO ESTA BIEN ESTE DOBLE REPO?

    public void saveCallHistory(String endpoint, int statusCode, String response) {
        Taxes callHistory = new Taxes();
        callHistory.setTimestamp(LocalDateTime.now().toString());
        callHistory.setEndpoint(endpoint);
        callHistory.setStatusCode(statusCode);
        callHistory.setResponse(response);

        taxRepository.save(callHistory);
    }

    public Page<Taxes> getCallHistory(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("timestamp").descending());
        Page<Taxes> callHistoryPage = callHistoryRepository.findAll(pageable);

        //TODO CAST TO TAXDTO
        return callHistoryPage;
      //  return callHistoryPage.map(this::mapToDTO);
    }

    private TaxesDTO mapToDTO(Taxes callHistory) {
        TaxesDTO dto = new TaxesDTO();
        dto.setTimestamp(callHistory.getTimestamp().toString());
        dto.setEndpoint(callHistory.getEndpoint());
        dto.setStatusCode(callHistory.getStatusCode());
        dto.setResponse(callHistory.getResponse());
        return dto;
    }
}

