package com.mehmetgenc.restaurantservice.advice;


import com.mehmetgenc.restaurantservice.exception.RestaurantNotFoundException;
import com.mehmetgenc.restaurantservice.exception.RestaurantServiceException;
import com.mehmetgenc.restaurantservice.general.GeneralErrorMessages;
import com.mehmetgenc.restaurantservice.general.KafkaProducerService;
import com.mehmetgenc.restaurantservice.general.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final KafkaProducerService kafkaProducerService;

    public GlobalExceptionHandler(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleRestaurantNotFoundException(RestaurantNotFoundException exception, WebRequest request){
        String message = exception.getMessage();
        String description = request.getDescription(false);
        GeneralErrorMessages generalErrorMessages = new GeneralErrorMessages(LocalDateTime.now(), message, description);
        RestResponse restResponse = RestResponse.error(generalErrorMessages);
        kafkaProducerService.sendMessage("errorLog", message);
        return new ResponseEntity<>(restResponse, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler
    public final ResponseEntity<Object> handleAllExceptions(Exception e, WebRequest request){
        String message = e.getMessage();
        String description = request.getDescription(false);

        GeneralErrorMessages generalErrorMessages = new GeneralErrorMessages(LocalDateTime.now(), message, description);
        RestResponse restResponse = RestResponse.error(generalErrorMessages);
        kafkaProducerService.sendMessage("errorLog", message);
        return new ResponseEntity<>(restResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleRunTimeExceptions(RuntimeException e, WebRequest request){
        String message = e.getMessage();
        String description = request.getDescription(false);

        GeneralErrorMessages generalErrorMessages = new GeneralErrorMessages(LocalDateTime.now(), message, description);
        RestResponse restResponse = RestResponse.error(generalErrorMessages);
        kafkaProducerService.sendMessage("errorLog", message);
        return new ResponseEntity<>(restResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleRestaurantServiceExceptions(RestaurantServiceException e, WebRequest request){
        String message = e.getMessage();
        String description = request.getDescription(false);

        GeneralErrorMessages generalErrorMessages = new GeneralErrorMessages(LocalDateTime.now(), message, description);
        RestResponse restResponse = RestResponse.error(generalErrorMessages);
        kafkaProducerService.sendMessage("errorLog", message);
        return new ResponseEntity<>(restResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
