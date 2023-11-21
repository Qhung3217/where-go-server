package com.wherego.wheregoserver.exception;


import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class MissingParamsException extends RuntimeException implements BaseException{
    private static final long serialVersion = 1L;
    private String[] params;

    public MissingParamsException( String[] params) {
        super("Missing required params: " + String.join(",", params));
        this.params = params;
    }

    public HttpStatus getStatus() {
        return HttpStatus.UNPROCESSABLE_ENTITY;
    }
}
