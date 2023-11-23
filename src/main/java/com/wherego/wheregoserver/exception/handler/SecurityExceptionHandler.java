package com.wherego.wheregoserver.exception.handler;

import com.wherego.wheregoserver.dto.ResponseMessageDto;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public abstract class SecurityExceptionHandler {
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ResponseMessageDto> handleBadCredentialsException(BadCredentialsException ex) {
        ResponseMessageDto msg = new ResponseMessageDto(HttpStatus.UNAUTHORIZED,
                ex.getMessage());
        return new ResponseEntity<>(msg, msg.getStatus());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ResponseMessageDto> handleAccessDeniedException(AccessDeniedException ex) {
        ResponseMessageDto msg = new ResponseMessageDto(HttpStatus.FORBIDDEN,
                "Access denied");
        return new ResponseEntity<>(msg, msg.getStatus());
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ResponseMessageDto> handleSignatureException(SignatureException ex) {
        ResponseMessageDto msg = new ResponseMessageDto(HttpStatus.FORBIDDEN,
                "JWT Signature is invalid");
        return new ResponseEntity<>(msg, msg.getStatus());
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ResponseMessageDto> handleExpiredJwtException(ExpiredJwtException ex) {
        ResponseMessageDto msg = new ResponseMessageDto(HttpStatus.FORBIDDEN,
                "JWT token already expired");
        return new ResponseEntity<>(msg, msg.getStatus());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ResponseMessageDto> handleAuthenticationException(AuthenticationException ex) {
        ResponseMessageDto msg = new ResponseMessageDto(HttpStatus.FORBIDDEN,
                ex.getMessage());
        return new ResponseEntity<>(msg, msg.getStatus());
    }
}
