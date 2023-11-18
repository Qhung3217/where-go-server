package com.wherego.wheregoserver.service;

import com.wherego.wheregoserver.dto.article.SimpleArticleDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArticleService {
    List<SimpleArticleDto> getAll();

    List<SimpleArticleDto> getRandom(Integer quantity);
}
