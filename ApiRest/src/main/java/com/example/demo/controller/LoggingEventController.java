package com.example.demo.controller;

import com.example.demo.model.jpa.CallHistory;
import com.example.demo.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;

@CrossOrigin
@RestController
@RequestMapping("/api/")
public class LoggingEventController {

@Autowired
HistoryService historyService;
    Gson gson = new Gson();

    @GetMapping("history")
    public ResponseEntity<String> getCallHistory(@RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "10") int size) {
        Page<CallHistory> historyPage = historyService.getCallHistory(page, size);
        String responseJSON = gson.toJson(historyPage.getContent());
        return ResponseEntity.ok(responseJSON);
    }
}

