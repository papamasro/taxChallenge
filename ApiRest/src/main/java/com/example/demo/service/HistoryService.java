package com.example.demo.service;

import com.example.demo.dto.HistoryDTO;
import com.example.demo.model.jpa.CallHistory;
import com.example.demo.repository.HistoryPagRepository;
import com.example.demo.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@Service
public class HistoryService {

    @Autowired
    private HistoryPagRepository callHistoryPagRepository;

    @Autowired
    private HistoryRepository historyRepository; //TODO ESTA BIEN ESTE DOBLE REPO?

    public void saveCallHistory(String endpoint, int statusCode, String response) {
        CompletableFuture<Integer> responseBD
                = CompletableFuture.supplyAsync(() -> {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Long time = timestamp.getTime();
            CallHistory callHistory = new CallHistory();
            callHistory.setTimestamp(time.toString());
            callHistory.setEndpoint(endpoint);
            callHistory.setStatusCode(statusCode);
            callHistory.setResponse(response);
            historyRepository.save(callHistory);
            return 200;
        });
    }

    public Page<CallHistory> getCallHistory(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("timestamp").descending());
        Page<CallHistory> callHistoryPage = callHistoryPagRepository.findAll(pageable);
        saveCallHistory("getCallHistory",200,callHistoryPage.toString());
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

