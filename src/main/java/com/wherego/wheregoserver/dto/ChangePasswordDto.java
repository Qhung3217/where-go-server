package com.wherego.wheregoserver.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@NotBlank
@Builder
public class ChangePasswordDto {
    private String currentPassword;
    private String newPassword;
}
