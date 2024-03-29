package com.wherego.wheregoserver.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class InvalidFieldNameException extends IllegalArgumentException implements BaseException{
    public InvalidFieldNameException() {
        super("Some field names are misspelled or wrong type");
    }
    public InvalidFieldNameException(String message) {
        super(message);
    }
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
