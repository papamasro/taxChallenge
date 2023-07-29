package com.example.demo.service.api;

import com.example.demo.model.jpa.CallHistory;
import com.example.demo.repository.HistoryPagRepository;
import com.example.demo.repository.HistoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LoggingEventServiceTest {

    private LoggingEventService loggingEventService;
    private HistoryPagRepository callHistoryPagRepository;
    private HistoryRepository historyRepository;

    @BeforeEach
    public void setUp() {
        callHistoryPagRepository = mock(HistoryPagRepository.class);
        historyRepository = mock(HistoryRepository.class);
       // loggingEventService = new LoggingEventService(callHistoryPagRepository, historyRepository);
    }

    @Test
    public void testSaveCallHistory() {
        // Arrange
        String endpoint = "testEndpoint";
        int statusCode = 200;
        String response = "Test response";

        // Act
        loggingEventService.saveCallHistory(endpoint, statusCode, response);

        // Assert
        verify(historyRepository, times(1)).save(any(CallHistory.class));
        // Aquí también podrías verificar los registros de log utilizando un captor de log si es necesario.
    }

    @Test
    public void testGetCallHistory() throws ExecutionException, InterruptedException {
        // Arrange
        int page = 0;
        int size = 10;
        PageRequest pageable = PageRequest.of(page, size, Sort.by("timestamp").descending());
        CallHistory callHistory = new CallHistory();
        // Agrega los campos relevantes a callHistory

        // Crea una lista con el único objeto CallHistory para simular el resultado de la página
        Page<CallHistory> callHistoryPage = new PageImpl<>(Collections.singletonList(callHistory), pageable, 1);
        when(callHistoryPagRepository.findAll(pageable)).thenReturn(callHistoryPage);

        // Act
        CompletableFuture<Page<CallHistory>> future = CompletableFuture.supplyAsync(
                () -> loggingEventService.getCallHistory(page, size));
        Page<CallHistory> result = future.get(); // Espera al resultado

        // Assert
        verify(historyRepository, times(1)).save(any(CallHistory.class));
        assertEquals(callHistoryPage, result);
    }

    // Si deseas testear la función mapToDTO, puedes hacerlo separadamente, aunque en este caso no parece necesario.
}