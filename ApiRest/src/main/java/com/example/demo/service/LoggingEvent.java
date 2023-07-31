package com.example.demo.service;

import com.example.demo.model.jpa.CallHistory;
import org.springframework.data.domain.Page;

public interface LoggingEvent {

    void saveCallHistory(String endpoint, int statusCode, String response);

    Page<CallHistory> getSuccessCallHistory(int page, int size);
}
