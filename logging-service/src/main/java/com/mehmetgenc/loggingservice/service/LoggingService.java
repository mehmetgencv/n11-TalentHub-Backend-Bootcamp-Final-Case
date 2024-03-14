package com.mehmetgenc.loggingservice.service;

import com.mehmetgenc.loggingservice.entity.ErrorLog;
import com.mehmetgenc.loggingservice.entity.InfoLog;
import com.mehmetgenc.loggingservice.repository.ErrorLogRepository;
import com.mehmetgenc.loggingservice.repository.InfoLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoggingService {

    private final ErrorLogRepository errorLogRepository;
    private final InfoLogRepository infoLogRepository;

    public List<InfoLog> getAllInfoLogs() {
        return infoLogRepository.findAll();
    }

    public List<ErrorLog> getAllErrorLogs() {
        return errorLogRepository.findAll();
    }


}
