package com.caseproject.caseproject.exceptionHandlers.errors;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
