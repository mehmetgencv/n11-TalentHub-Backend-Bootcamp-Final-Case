package com.mehmetgenc.reviewservice.exception;

public class UserExistException extends ReviewServiceException {

    public UserExistException(String message) {
        super(message);
    }
}