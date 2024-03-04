package com.mehmetgenc.reviewservice.exception;

public class UserNotFoundException extends ReviewServiceException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
