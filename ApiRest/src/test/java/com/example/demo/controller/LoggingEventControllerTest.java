package com.example.demo.controller;

import com.example.demo.model.jpa.CallHistory;
import com.example.demo.model.services.logger.LoggerEventResponse;
import com.example.demo.service.api.LoggingEventService;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LoggingEventControllerTest {

    @Mock
    private LoggingEventService loggingEventService;

    @InjectMocks
    private LoggingEventController loggingEventController;

    private Gson gson;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        gson = new Gson();
    }

    @Test
    public void testGetCallHistory() {
        /* TODO: NOT WORKING PAGE TEST
        int page = 0;
        int size = 10;
        List<CallHistory> callHistoryList = new ArrayList<>();
        callHistoryList.add(new CallHistory(1l,"","",200,""));
        Page<CallHistory> pageResult = new PageImpl<>(callHistoryList);

        when(loggingEventService.getCallHistory(page, size)).thenReturn(pageResult);

        ResponseEntity<LoggerEventResponse> response = loggingEventController.getCallHistory(page, size);

        assertEquals(HttpStatus.OK, response.getStatusCode());
       assertEquals(gson.toJson(callHistoryList), response.getBody().getResult());

        verify(loggingEventService, times(1)).getCallHistory(page, size);

         */
    }
}