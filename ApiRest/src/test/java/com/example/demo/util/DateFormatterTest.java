package com.example.demo.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DateFormatterTest {

    @Test
    public void testGetStringDate() {
        String dateString = DateFormatter.getStringDate();

        assertNotNull(dateString);
    }
}