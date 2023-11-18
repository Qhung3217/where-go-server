package com.wherego.wheregoserver.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NotBlank
@Builder
public class AuthenticateResponseDto {
    private String username;
    private String token;
}
