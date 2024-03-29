package com.wherego.wheregoserver.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NotBlank
@Builder
public class ResponseMessageDto {
    private HttpStatus status;
    private String message;
}
