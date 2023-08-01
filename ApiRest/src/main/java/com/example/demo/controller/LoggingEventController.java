package com.example.demo.controller;

import com.example.demo.model.entity.CallHistoryEntity;
import com.example.demo.model.services.logger.LoggerEventResponse;
import com.example.demo.service.impl.api.LoggingEventService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/")
public class LoggingEventController {

    @Autowired
    LoggingEventService loggingEventService;
    Gson gson = new Gson();

    @GetMapping("history")
    public ResponseEntity<LoggerEventResponse> getCallHistory(@RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "10") int size) {
        Page<CallHistoryEntity> historyPage = loggingEventService.getSuccessCallHistory(page, size);
        LoggerEventResponse loggerEventResponse = new LoggerEventResponse(gson.toJson(historyPage.getContent()), historyPage.getNumber(), historyPage.getPageable().getPageSize(), historyPage.getTotalPages());
        return ResponseEntity.ok(loggerEventResponse);
    }
}

