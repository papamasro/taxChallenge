package com.example.demo.service;

import com.example.demo.model.entity.CallHistoryEntity;
import org.springframework.data.domain.Page;

public interface LoggingEvent {

    void saveCallHistory(String endpoint, int statusCode, String response);

    Page<CallHistoryEntity> getSuccessCallHistory(int page, int size);
}
