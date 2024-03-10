package com.mehmetgenc.reviewservice.advice;

import com.mehmetgenc.reviewservice.exception.ReviewNotFoundException;
import com.mehmetgenc.reviewservice.exception.ReviewServiceException;
import com.mehmetgenc.reviewservice.exception.UserNotFoundException;
import com.mehmetgenc.reviewservice.general.GeneralErrorMessages;
import com.mehmetgenc.reviewservice.general.RestResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> handleUserReviewNotFoundException(ReviewNotFoundException exception, WebRequest request){
        String message = exception.getMessage();
        String description = request.getDescription(false);
        GeneralErrorMessages generalErrorMessages = new GeneralErrorMessages(LocalDateTime.now(), message, description);
        RestResponse restResponse = RestResponse.error(generalErrorMessages);
        return new ResponseEntity<>(restResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception, WebRequest request){
        String message = exception.getMessage();
        String description = request.getDescription(false);
        GeneralErrorMessages generalErrorMessages = new GeneralErrorMessages(LocalDateTime.now(), message, description);
        RestResponse restResponse = RestResponse.error(generalErrorMessages);
        return new ResponseEntity<>(restResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleAllExceptions(Exception e, WebRequest request){
        String message = e.getMessage();
        String description = request.getDescription(false);

        GeneralErrorMessages generalErrorMessages = new GeneralErrorMessages(LocalDateTime.now(), message, description);
        RestResponse restResponse = RestResponse.error(generalErrorMessages);
        return new ResponseEntity<>(restResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = ex.getBindingResult().getFieldError().getDefaultMessage();
        String description = request.getDescription(false);
        GeneralErrorMessages generalErrorMessages = new GeneralErrorMessages(LocalDateTime.now(), message, description);
        RestResponse restResponse = RestResponse.error(generalErrorMessages);
        return new ResponseEntity<>(restResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleRunTimeExceptions(RuntimeException e, WebRequest request){
        String message = e.getMessage();
        String description = request.getDescription(false);

        GeneralErrorMessages generalErrorMessages = new GeneralErrorMessages(LocalDateTime.now(), message, description);
        RestResponse restResponse = RestResponse.error(generalErrorMessages);
        return new ResponseEntity<>(restResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleReviewServiceExceptions(ReviewServiceException e, WebRequest request){
        String message = e.getMessage();
        String description = request.getDescription(false);

        GeneralErrorMessages generalErrorMessages = new GeneralErrorMessages(LocalDateTime.now(), message, description);
        RestResponse restResponse = RestResponse.error(generalErrorMessages);
        return new ResponseEntity<>(restResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
