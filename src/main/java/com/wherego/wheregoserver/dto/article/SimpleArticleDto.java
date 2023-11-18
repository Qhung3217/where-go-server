package com.wherego.wheregoserver.dto.article;

import com.wherego.wheregoserver.dto.writer.SimpleWriterDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimpleArticleDto {
    private Long id;
    private String title;
    private String thumbnail;
    private String content;
    private String shortDescription;
    private SimpleWriterDto writer;
}