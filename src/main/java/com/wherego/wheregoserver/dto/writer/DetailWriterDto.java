package com.wherego.wheregoserver.dto.writer;

import com.wherego.wheregoserver.dto.article.DetailArticleNoAuthorDto;
import com.wherego.wheregoserver.dto.article.SimpleArticleDto;
import com.wherego.wheregoserver.repository.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetailWriterDto {
    private String email;
    private String name;
    private String tels;
    private String avatar;
    private Date dob;
    private String username;
    private Set<DetailArticleNoAuthorDto> articles;
}
