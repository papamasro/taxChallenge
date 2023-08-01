package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.demo.model.entity.CallHistoryEntity;
import com.example.demo.model.services.logger.LoggerEventResponse;
import com.example.demo.repository.HistoryPagRepository;
import com.example.demo.service.impl.api.LoggingEventService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class LoggingEventControllerTest {
    @Mock
    private LoggingEventService loggingEventService;


    @Mock
    private HistoryPagRepository callHistoryPagRepository;
    @InjectMocks
    private LoggingEventController loggingEventController;
/*
    @Test
    void testGetCallHistory() {
        // Arrange
        int page = 0;
        int size = 10;
        List<CallHistoryEntity> callHistoryEntities = new ArrayList<>();
        callHistoryEntities.add(new CallHistoryEntity());
        // Add more call history entities as needed

        Page<CallHistoryEntity> historyPage = mock(Page.class);
        when(historyPage.getContent()).thenReturn(callHistoryEntities);

        // Create an instance of Pageable using PageRequest
        Pageable pageable = PageRequest.of(page, size, Sort.by("timestamp").descending());

        // Simulate the behavior of findAllByStatusCode with the created Pageable
        when(callHistoryPagRepository.findAllByStatusCode(200, pageable)).thenReturn(historyPage);

        // Act
        ResponseEntity<LoggerEventResponse> responseEntity = loggingEventController.getCallHistory(page, size);

        // Assert
        assertEquals(200, responseEntity.getStatusCodeValue());
        LoggerEventResponse loggerEventResponse = responseEntity.getBody();
        assertNotNull(loggerEventResponse);
        assertEquals(1, loggerEventResponse.getPageNumber());
        assertEquals(size, loggerEventResponse.getPageSize());
        assertEquals(1, loggerEventResponse.getTotalPages());
    }
    */
}
