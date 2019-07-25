package com.bin448.backend.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class MyExceptionHandler {

//    @ExceptionHandler(value = Exception.class)
//    public ResponseEntity<ExceptionWrapper> handleInternalServerExceptionException(Exception e) {
//        return createResponseEntity(createExceptionWrapper(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
//    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ExceptionWrapper> handleNotFoundException(NotFoundException nfe) {
        return createResponseEntity(createExceptionWrapper(nfe.getMessage(), HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionWrapper> handleNotValidException(MethodArgumentNotValidException mnve) {
        String errorMessage = mnve.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));
        return createResponseEntity(createExceptionWrapper(errorMessage, HttpStatus.BAD_REQUEST));
    }

    private ResponseEntity<ExceptionWrapper> createResponseEntity(ExceptionWrapper exceptionWrapper) {
        return new ResponseEntity<>(exceptionWrapper, exceptionWrapper.getStatus());
    }

    private ExceptionWrapper createExceptionWrapper(String message, HttpStatus status) {
        return new ExceptionWrapper(message, status);
    }
}
