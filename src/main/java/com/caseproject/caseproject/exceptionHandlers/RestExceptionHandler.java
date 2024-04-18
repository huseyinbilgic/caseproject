package com.caseproject.caseproject.exceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.caseproject.caseproject.exceptionHandlers.errors.NotFoundException;

@ControllerAdvice
public class RestExceptionHandler {
    
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(NotFoundException exc) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setHttpStatus(HttpStatus.NOT_FOUND);
        errorResponse.setMessage(exc.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
