package com.wherego.wheregoserver.exception;

import com.wherego.wheregoserver.dto.ResponseMessageDto;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

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
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseMessageDto> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        ResponseMessageDto msg = new ResponseMessageDto(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(msg, msg.getStatus());
    }

    //------------------------SECURITY EXCEPTIONS------------------------
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ResponseMessageDto> handleBadCredentialsException(BadCredentialsException ex) {
        ResponseMessageDto msg = new ResponseMessageDto(HttpStatus.UNAUTHORIZED,
                ex.getMessage());
        return new ResponseEntity<>(msg, msg.getStatus());
    }
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ResponseMessageDto> handleAccessDeniedException(AccessDeniedException ex){
        ResponseMessageDto msg = new ResponseMessageDto(HttpStatus.FORBIDDEN,
               "Not Authorized");
        return new ResponseEntity<>(msg, msg.getStatus());
    }
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ResponseMessageDto> handleAuthenticationException(AuthenticationException ex){
        ResponseMessageDto msg = new ResponseMessageDto(HttpStatus.FORBIDDEN,
               ex.getMessage());
        return new ResponseEntity<>(msg, msg.getStatus());
    }
    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ResponseMessageDto> handleSignatureException(SignatureException ex){
        ResponseMessageDto msg = new ResponseMessageDto(HttpStatus.FORBIDDEN,
                "JWT Signature is invalid");
        return new ResponseEntity<>(msg, msg.getStatus());
    }
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ResponseMessageDto> handleExpiredJwtException(ExpiredJwtException ex){
        ResponseMessageDto msg = new ResponseMessageDto(HttpStatus.FORBIDDEN,
                "JWT token already expired");
        return new ResponseEntity<>(msg, msg.getStatus());
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ResponseMessageDto> handleException(Exception ex){
//        ResponseMessageDto msg = new ResponseMessageDto(HttpStatus.INTERNAL_SERVER_ERROR,
//                ex.getMessage());
//        return new ResponseEntity<>(msg, msg.getStatus());
//    }
}
