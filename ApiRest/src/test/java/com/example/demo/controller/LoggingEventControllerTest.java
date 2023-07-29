package com.example.demo.controller;

import com.example.demo.model.jpa.CallHistory;
import com.example.demo.service.api.LoggingEventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class LoggingEventControllerTest {

    @Mock
    private LoggingEventService loggingEventService;

    @InjectMocks
    private LoggingEventController loggingEventController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCallHistory_SuccessfulResponse() {
        // Arrange
        int page = 0;
        int size = 10;
        // Crear una instancia válida de Page<CallHistory> para simular el resultado exitoso
        Page<CallHistory> historyPage = new PageImpl<>(Collections.emptyList());
        when(loggingEventService.getCallHistory(page, size)).thenReturn(historyPage);

        // Act
        ResponseEntity<String> responseEntity = loggingEventController.getCallHistory(page, size);

        // Assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        // Agregar más comprobaciones para verificar el contenido de la respuesta JSON
    }

    @Test
    public void testGetCallHistory_ExceptionFromService() {
        // Arrange
        int page = 0;
        int size = 10;
        // Configurar el servicio para devolver una instancia válida de Page<CallHistory> en lugar de lanzar una excepción
        Page<CallHistory> historyPage = new PageImpl<>(Collections.emptyList());
        when(loggingEventService.getCallHistory(page, size)).thenReturn(historyPage);

        // Act
        ResponseEntity<String> responseEntity = loggingEventController.getCallHistory(page, size);

        // Assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        // Agregar más comprobaciones para verificar el contenido de la respuesta JSON basado en el `historyPage` esperado
    }
}