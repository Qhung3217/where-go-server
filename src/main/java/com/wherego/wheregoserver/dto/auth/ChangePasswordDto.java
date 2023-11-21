package com.wherego.wheregoserver.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@NotBlank
@Builder
public class ChangePasswordDto {
    private String currentPassword;
    private String newPassword;
}
