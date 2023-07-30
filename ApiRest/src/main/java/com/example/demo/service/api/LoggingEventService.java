package com.example.demo.service.api;

import com.example.demo.model.jpa.CallHistory;
import com.example.demo.repository.HistoryPagRepository;
import com.example.demo.repository.HistoryRepository;
import com.example.demo.util.DateFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class LoggingEventService {

    private static final Logger logger = LoggerFactory.getLogger(LoggingEventService.class);

    @Autowired
    private HistoryPagRepository callHistoryPagRepository;

    @Autowired
    private HistoryRepository historyRepository;

    public void saveCallHistory(String endpoint, int statusCode, String response) {
        CallHistory callHistory = new CallHistory();
        callHistory.setTimestamp(DateFormatter.getStringDate());
        callHistory.setEndpoint(endpoint);
        callHistory.setStatusCode(statusCode);
        callHistory.setResponse(response);
        logger.info("saving history call on BD");
        historyRepository.save(callHistory);
    }

    public Page<CallHistory> getCallHistory(int page, int size) {
        logger.info("getting history calls from BD");
        Pageable pageable = PageRequest.of(page, size, Sort.by("timestamp").descending());
        Page<CallHistory> callHistoryPage = callHistoryPagRepository.findAll(pageable);
        saveCallHistory("getCallHistory", 200, callHistoryPage.toString());
        logger.info("success getting history calls of BD");
        return callHistoryPage;
    }
}

