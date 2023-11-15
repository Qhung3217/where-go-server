package com.wherego.wheregoserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WriterDto {
    private String email;
    private String name;
    private String tels;
    private String avatar;
    private Date dob;
    private String username;
    private String password;
}
