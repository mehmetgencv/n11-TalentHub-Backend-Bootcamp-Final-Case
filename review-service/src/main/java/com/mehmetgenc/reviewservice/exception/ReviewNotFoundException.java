package com.mehmetgenc.reviewservice.exception;

public class ReviewNotFoundException extends ReviewServiceException {
    public ReviewNotFoundException(String message) {
        super(message);
    }
}
