package com.example.demo.service.api;

import com.example.demo.repository.PercentageCacheRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class PercentageCacheServiceTest {

    @Mock
    private PercentageCacheRepository percentageCacheRepository;

    @InjectMocks
    private PercentageCacheService percentageCacheService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetPercentage_WithValidValue_ReturnsValue() {
        // Arrange
        double percentageValue = 0.15;
        when(percentageCacheRepository.getKey(anyString())).thenReturn(percentageValue);

        // Act
        Optional<Double> result = percentageCacheService.getPercentage();

        // Assert
        assertTrue(result.isPresent());
        assertEquals(percentageValue, result.get());
        verify(percentageCacheRepository, times(1)).getKey(anyString());
    }

    @Test
    public void testGetPercentage_WithNullValue_ReturnsEmptyOptional() {
        // Arrange
        when(percentageCacheRepository.getKey(anyString())).thenReturn(null);

        // Act
        Optional<Double> result = percentageCacheService.getPercentage();

        // Assert
        assertFalse(result.isPresent());
        verify(percentageCacheRepository, times(1)).getKey(anyString());
    }

    @Test
    public void testSavePercentage_CallsRepositoryWithCorrectParameters() {
        // Arrange
        double percentageValue = 0.20;

        // Act
        percentageCacheService.savePercentage(percentageValue);

        // Assert
        verify(percentageCacheRepository, times(1)).saveIfAbsent(eq("PERCENTAGE_KEY"), eq(percentageValue), any(Duration.class));
    }
}