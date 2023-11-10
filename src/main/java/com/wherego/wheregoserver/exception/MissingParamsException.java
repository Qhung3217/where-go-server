package com.wherego.wheregoserver.exception;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class MissingParamsException extends RuntimeException{
    private static final long serialVersion = 1L;
    private String[] params;

    public MissingParamsException( String[] params) {
        super(convertParamsToMessage(params));
        this.params = params;
    }


    private static String convertParamsToMessage(String[] params) {
        StringBuilder message= new StringBuilder();
        for (String param : params) {
            message.append("Missing required param: ").append(param).append("\n");
        }
        return message.toString();
    }
}
