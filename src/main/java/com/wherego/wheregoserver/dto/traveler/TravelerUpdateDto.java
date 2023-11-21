package com.wherego.wheregoserver.dto.traveler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TravelerUpdateDto {
    private String name;
    private String tels;
    private Date dob;
    private String username;
    private MultipartFile avatarFile;
}
