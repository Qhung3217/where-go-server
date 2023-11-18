package com.wherego.wheregoserver.dto.writer;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WriterUpdateDto {
    private String name;
    private String tels;
    private Date dob;
    private String username;
}
