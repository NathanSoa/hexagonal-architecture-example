package com.example.hexagonalarchitecture.config.exception;

import com.example.hexagonalarchitecture.config.exception.custom.EntityDoesNotExistException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({EntityDoesNotExistException.class})
    public ResponseEntity<Object> handleBadCredentialsException(EntityDoesNotExistException e, WebRequest request) {
        Error error = new Error("Entity does not exist", e.getMessage());
        return handleExceptionInternal(e, error, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }
}
