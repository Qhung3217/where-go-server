package com.wherego.wheregoserver.dto.writer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WriterRegisterDto {
    private String email;
    private String name;
    private String tels;
    private MultipartFile avatarFile;
    private Date dob;
    private String username;
    private String password;
}
