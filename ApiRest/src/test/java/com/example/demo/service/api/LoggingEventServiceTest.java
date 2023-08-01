package com.example.demo.service.api;

import com.example.demo.repository.HistoryPagRepository;
import com.example.demo.repository.HistoryRepository;
import com.example.demo.service.impl.api.LoggingEventService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.demo.model.entity.CallHistoryEntity;
import com.example.demo.repository.HistoryPagRepository;
import com.example.demo.repository.HistoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class LoggingEventServiceTest {

    @Mock
    private HistoryPagRepository callHistoryPagRepository;

    @Mock
    private HistoryRepository historyRepository;

    @Mock
    private Logger logger;

    @InjectMocks
    private LoggingEventService loggingEventService;

    @Test
    void testGetSuccessCallHistory() {
        // Arrange
        int page = 0;
        int size = 10;
        List<CallHistoryEntity> callHistoryEntities = new ArrayList<>();
        callHistoryEntities.add(new CallHistoryEntity(/* ... */));
        // Add more call history entities as needed

        Page<CallHistoryEntity> callHistoryPage = mock(Page.class);
     //   when(callHistoryPage.getContent()).thenReturn(callHistoryEntities);

        Pageable pageable = PageRequest.of(page, size, Sort.by("timestamp").descending());
        when(callHistoryPagRepository.findAllByStatusCode(200, pageable)).thenReturn(callHistoryPage);

        // Act
        Page<CallHistoryEntity> resultPage = loggingEventService.getSuccessCallHistory(page, size);

        // Assert
        assertNotNull(resultPage);
        assertEquals(callHistoryPage, resultPage);
        // You can add more assertions based on your implementation and expected output
    }
}