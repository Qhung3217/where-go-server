package com.wherego.wheregoserver.exception.handler;

import com.wherego.wheregoserver.dto.ResponseMessageDto;
import com.wherego.wheregoserver.exception.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler extends SecurityExceptionHandler {

    //------------------------CUSTOM EXCEPTION HANDLER------------------------
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

    @ExceptionHandler(ResourceInvalidException.class)
    public ResponseEntity<ResponseMessageDto> handleResourceInvalidException(ResourceInvalidException ex) {
        ResponseMessageDto msg = new ResponseMessageDto(ex.getStatus(), ex.getMessage());
        return new ResponseEntity<>(msg, msg.getStatus());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ResponseMessageDto> handleUserNotFoundException(UserNotFoundException ex) {
        ResponseMessageDto msg = new ResponseMessageDto(ex.getStatus(), ex.getMessage());
        return new ResponseEntity<>(msg, msg.getStatus());
    }

    @ExceptionHandler(InvalidFieldNameException.class)
    public ResponseEntity<ResponseMessageDto> handleInvalidFieldNameException(InvalidFieldNameException ex) {
        ResponseMessageDto msg = new ResponseMessageDto(ex.getStatus(), ex.getMessage());
        return new ResponseEntity<>(msg, msg.getStatus());
    }
    @ExceptionHandler(InvalidFieldValueException.class)
    public ResponseEntity<ResponseMessageDto> handleInvalidFieldValueException(InvalidFieldValueException ex) {
        ResponseMessageDto msg = new ResponseMessageDto(ex.getStatus(), ex.getMessage());
        return new ResponseEntity<>(msg, msg.getStatus());
    }

    //------------------------END CUSTOM EXCEPTION HANDLER------------------------

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseMessageDto> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        ResponseMessageDto msg = new ResponseMessageDto(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(msg, msg.getStatus());
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ResponseMessageDto> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
        ResponseMessageDto msg = new ResponseMessageDto(HttpStatus.UNSUPPORTED_MEDIA_TYPE,
                ex.getMessage());
        return new ResponseEntity<>(msg, msg.getStatus());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResponseMessageDto> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        ResponseMessageDto msg = new ResponseMessageDto(HttpStatus.CONFLICT,
                "Duplicate primary key");
        return new ResponseEntity<>(msg, msg.getStatus());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ResponseMessageDto> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        ResponseMessageDto msg = new ResponseMessageDto(HttpStatus.BAD_REQUEST,
                ex.getMessage());
        return new ResponseEntity<>(msg, msg.getStatus());
    }


    //------------------------DEFAULT EXCEPTION HANDLER------------------------
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> defaultErrorHandler(WebRequest req, Exception e) throws Exception {
        ResponseMessageDto msg = new ResponseMessageDto(HttpStatus.INTERNAL_SERVER_ERROR,
                e.getMessage());
        e.printStackTrace();
        return new ResponseEntity<>(msg, msg.getStatus());

    }
}
