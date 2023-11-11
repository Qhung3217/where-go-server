package com.wherego.wheregoserver.exception;

import com.wherego.wheregoserver.dto.ResponseMessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseMessageDto> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ResponseMessageDto msg = new ResponseMessageDto(ex.getStatus(), ex.getMessage());
        return new ResponseEntity<>(msg, msg.getStatus());
    }
    @ExceptionHandler(MissingParamsException.class)
    public ResponseEntity<ResponseMessageDto> handleMissingParamsException(MissingParamsException ex) {
        ResponseMessageDto msg = new ResponseMessageDto(ex.getStatus(), ex.getMessage());
        return new ResponseEntity<>(msg, msg.getStatus());
    }
}
