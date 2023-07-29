package com.example.demo.service.api;

import com.example.demo.dto.HistoryDTO;
import com.example.demo.model.jpa.CallHistory;
import com.example.demo.repository.HistoryPagRepository;
import com.example.demo.repository.HistoryRepository;
import com.example.demo.service.TaxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.concurrent.CompletableFuture;

@Service
public class LoggingEventService {

    private static final Logger logger = LoggerFactory.getLogger(TaxService.class);

    @Autowired
    private HistoryPagRepository callHistoryPagRepository;

    @Autowired
    private HistoryRepository historyRepository; //TODO ESTA BIEN ESTE DOBLE REPO?

    public void saveCallHistory(String endpoint, int statusCode, String response) {
         CompletableFuture.supplyAsync(() -> {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Long time = timestamp.getTime();
            CallHistory callHistory = new CallHistory();
            callHistory.setTimestamp(time.toString());
            callHistory.setEndpoint(endpoint);
            callHistory.setStatusCode(statusCode);
            callHistory.setResponse(response);
            logger.info("saving history call on BD");
            historyRepository.save(callHistory);
            return 200;
        }).thenApply(s -> {
            logger.info("success saving history call on BD");
            return s;
        });


    }

    public Page<CallHistory> getCallHistory(int page, int size) {
        logger.info("getting history call of BD");

        Pageable pageable = PageRequest.of(page, size, Sort.by("timestamp").descending());
        Page<CallHistory> callHistoryPage = callHistoryPagRepository.findAll(pageable);
        saveCallHistory("getCallHistory",200,callHistoryPage.toString());
        logger.info("success getting history calls of BD");

        //TODO CAST
        return callHistoryPage;
      //  return callHistoryPage.map(this::mapToDTO);
    }

    private HistoryDTO mapToDTO(CallHistory callHistory) {
        HistoryDTO dto = new HistoryDTO();
        dto.setTimestamp(callHistory.getTimestamp().toString());
        dto.setEndpoint(callHistory.getEndpoint());
        dto.setStatusCode(callHistory.getStatusCode());
        dto.setResponse(callHistory.getResponse());
        return dto;
    }
}

