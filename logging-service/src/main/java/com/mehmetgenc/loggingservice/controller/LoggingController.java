package com.mehmetgenc.loggingservice.controller;

import com.mehmetgenc.loggingservice.entity.ErrorLog;
import com.mehmetgenc.loggingservice.entity.InfoLog;
import com.mehmetgenc.loggingservice.general.RestResponse;
import com.mehmetgenc.loggingservice.service.LoggingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/logging")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class LoggingController {

    private final LoggingService loggingService;

    @GetMapping("/InfoLogs")
    public ResponseEntity<RestResponse<List<InfoLog>>> getAllInfoLogs() {
        return ResponseEntity.ok(RestResponse.of(loggingService.getAllInfoLogs()));
    }
    
    @GetMapping("/ErrorLogs")
    public ResponseEntity<RestResponse<List<ErrorLog>>> getAllErrorLogs() {
        return ResponseEntity.ok(RestResponse.of(loggingService.getAllErrorLogs()));
    }
}
