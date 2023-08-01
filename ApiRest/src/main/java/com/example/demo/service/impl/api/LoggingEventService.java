package com.example.demo.service.impl.api;

import com.example.demo.model.entity.CallHistoryEntity;
import com.example.demo.repository.HistoryPagRepository;
import com.example.demo.repository.HistoryRepository;
import com.example.demo.service.LoggingEvent;
import com.example.demo.util.DateFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class LoggingEventService implements LoggingEvent {

    private static final Logger logger = LoggerFactory.getLogger(LoggingEventService.class);

    @Autowired
    private HistoryPagRepository callHistoryPagRepository;

    @Autowired
    private HistoryRepository historyRepository;

    public void saveCallHistory(String endpoint, int statusCode, String response) {
        CallHistoryEntity callHistoryEntity = new CallHistoryEntity();
        callHistoryEntity.setTimestamp(DateFormatter.getStringDate());
        callHistoryEntity.setEndpoint(endpoint);
        callHistoryEntity.setStatusCode(statusCode);
        callHistoryEntity.setResponse(response);
        String msg = "saving history service call with endpoint: "+ endpoint + " and status code " + statusCode;
        logger.info(msg);
        historyRepository.save(callHistoryEntity);
    }

    public Page<CallHistoryEntity> getSuccessCallHistory(int page, int size) {
        logger.info("getting history calls from BD");
        Pageable pageable = PageRequest.of(page, size, Sort.by("timestamp").descending());
        Page<CallHistoryEntity> callHistoryPage = callHistoryPagRepository.findAllByStatusCode(200,pageable);
        saveCallHistory("getCallHistory", 200, callHistoryPage.toString()); //TODO: RESPONSE TOO LONG TO SAVE
        logger.info("success getting history calls of BD");
        return callHistoryPage;
    }
}

