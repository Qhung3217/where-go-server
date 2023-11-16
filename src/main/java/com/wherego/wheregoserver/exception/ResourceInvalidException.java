package com.wherego.wheregoserver.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ResourceInvalidException extends RuntimeException implements BaseException{
    private String resourceName;
    private String resourceValue;

    public ResourceInvalidException(String resourceName, String resourceValue) {
        super(String.format("Invalid %s: %s", resourceName, resourceValue));
        this.resourceName = resourceName;
        this.resourceValue = resourceValue;
    }

    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }

}
