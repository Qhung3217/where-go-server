package com.wherego.wheregoserver.dto.article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetailArticleNoAuthorDto {
    private Long id;
    private String title;
    private String thumbnail;
    private String content;
    private String shortDescription;
    private Date createDate;
}
