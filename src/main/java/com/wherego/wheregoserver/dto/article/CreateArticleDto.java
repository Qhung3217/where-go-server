package com.wherego.wheregoserver.dto.article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateArticleDto {
    private String title;
    private MultipartFile thumbnail;
    private String content;
    private String shortDescription;
}
