package com.wherego.wheregoserver.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@NoArgsConstructor
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException implements BaseException{
    private String userRole;

    public UserNotFoundException( String userRole) {
        super(String.format("%s not found", userRole));
        this.userRole = userRole;
    }

    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
