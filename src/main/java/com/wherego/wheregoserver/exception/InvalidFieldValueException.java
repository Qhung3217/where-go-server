package com.wherego.wheregoserver.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.stream.Collectors;

@Getter
@Setter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidFieldValueException extends RuntimeException implements BaseException{
    private String[] fieldName;

    public InvalidFieldValueException(String[] fieldName) {
        super("The following fields contain invalid values: "+ String.join(",", fieldName));
        this.fieldName = fieldName;
    }

    public InvalidFieldValueException(String message, String[] fieldName) {
        super(message);
        this.fieldName = fieldName;
    }
    public InvalidFieldValueException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
