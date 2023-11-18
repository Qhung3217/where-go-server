package com.wherego.wheregoserver.dto.writer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SimpleWriterDto {
    private String name;
    private String tels;
    private String avatar;
    private Date dob;
    private String username;
}
