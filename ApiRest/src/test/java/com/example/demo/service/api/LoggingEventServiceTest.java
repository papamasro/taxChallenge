package com.example.demo.service.api;

import com.example.demo.model.jpa.CallHistory;
import com.example.demo.repository.HistoryPagRepository;
import com.example.demo.repository.HistoryRepository;
import com.example.demo.service.impl.api.LoggingEventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class LoggingEventServiceTest {

    @Mock
    private HistoryPagRepository callHistoryPagRepository;

    @Mock
    private HistoryRepository historyRepository;

    @InjectMocks
    private LoggingEventService loggingEventService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    /*
    @Test
    public void testSaveCallHistory() {
        // Arrange
        String endpoint = "testEndpoint";
        int statusCode = 200;
        String response = "testResponse";

        // Act
        loggingEventService.saveCallHistory(endpoint, statusCode, response);

        // Assert
        verify(historyRepository, times(1)).save(any(CallHistory.class));
    }

    @Test
    public void testGetCallHistory() {
        // Arrange
        int page = 0;
        int size = 10;
        List<CallHistory> callHistoryList = new ArrayList<>();
        callHistoryList.add(new CallHistory());
        Page<CallHistory> pageResult = new PageImpl<>(callHistoryList);

        Pageable pageable = PageRequest.of(page, size, Sort.by("timestamp").descending());
        when(callHistoryPagRepository.findAll(pageable)).thenReturn(pageResult);

        Page<CallHistory> result = loggingEventService.getSuccessCallHistory(page, size);

        assertEquals(pageResult, result);

        verify(callHistoryPagRepository, times(1)).findAll(pageable);
        verify(historyRepository, times(1)).save(any(CallHistory.class));
    }

    @Test
    public void testGetCallHistoryWithEmptyPage() {
        // Arrange
        int page = 0;
        int size = 10;
        List<CallHistory> callHistoryList = new ArrayList<>();
        Page<CallHistory> emptyPage = new PageImpl<>(callHistoryList);

        Pageable pageable = PageRequest.of(page, size, Sort.by("timestamp").descending());
        when(callHistoryPagRepository.findAll(pageable)).thenReturn(emptyPage);

        Page<CallHistory> result = loggingEventService.getSuccessCallHistory(page, size);

        assertTrue(result.isEmpty());

        verify(callHistoryPagRepository, times(1)).findAll(pageable);
        verify(historyRepository, times(1)).save(any(CallHistory.class));
    }
    */
}