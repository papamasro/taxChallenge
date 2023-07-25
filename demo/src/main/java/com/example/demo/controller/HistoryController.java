package com.example.demo.controller;

import com.example.demo.model.CalculateTaxRequest;
import com.example.demo.model.CalculateTaxResponse;
import com.example.demo.model.database.jpa.Taxes;
import com.example.demo.model.external.ChargesRequest;
import com.example.demo.service.CalculatorService;
import com.example.demo.service.HistoryService;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/")
public class HistoryController {

@Autowired
HistoryService historyService;


    @GetMapping("history")
    public ResponseEntity<Page<Taxes>> getCallHistory(@RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "10") int size) {
        Page<Taxes> historyPage = historyService.getCallHistory(page, size);
        return ResponseEntity.ok(historyPage);
    }
}

