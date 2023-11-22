package com.wherego.wheregoserver.service;

import com.wherego.wheregoserver.dto.ResponseMessageDto;
import com.wherego.wheregoserver.dto.article.CreateArticleDto;
import com.wherego.wheregoserver.dto.article.DetailArticleDto;
import com.wherego.wheregoserver.dto.article.SimpleArticleDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArticleService {
    List<SimpleArticleDto> getAll();

    DetailArticleDto getById(Long id);

    List<SimpleArticleDto> getRandom(Integer quantity);

    List<SimpleArticleDto> search(String keyword);

    ResponseMessageDto create(String token, CreateArticleDto createArticleDto);

    ResponseMessageDto update(String token, CreateArticleDto updateArticle, Long articleId);
}
