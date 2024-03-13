package com.mehmetgenc.loggingservice.service;

import java.time.LocalDateTime;

import com.mehmetgenc.loggingservice.entity.ErrorLog;
import com.mehmetgenc.loggingservice.entity.InfoLog;
import com.mehmetgenc.loggingservice.repository.ErrorLogRepository;
import com.mehmetgenc.loggingservice.repository.InfoLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final ErrorLogRepository errorLogRepository;
    private final InfoLogRepository infoLogRepository;

    @KafkaListener(topics = "errorLog", groupId = "log-consumer-group")
    public void consume(String message){

        log.info("consume started!");

        ErrorLog errorLog = new ErrorLog();
        errorLog.setDate(LocalDateTime.now());
        errorLog.setMessage(message);
        errorLog.setDescription("Error");

        errorLogRepository.save(errorLog);

        log.info("consume finished!");

    }

    @KafkaListener(topics = "errorLog.DLT", groupId = "log-consumer-group-dlt")
    public void consumeDLT(String message){
        log.error("Received message from DLT Queue " + message);
    }

    @KafkaListener(topics = "infoLog", groupId = "log-consumer-group")
    public void consumeInfos(String message){

        log.info("consume started!");

        InfoLog infoLog = new InfoLog();
        infoLog.setDate(LocalDateTime.now());
        infoLog.setMessage(message);
        infoLog.setDescription("Info");

        infoLogRepository.save(infoLog);

        log.info("consume finished!");

    }
}