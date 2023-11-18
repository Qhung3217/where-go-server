package com.wherego.wheregoserver.service.impl;

import com.wherego.wheregoserver.dto.article.SimpleArticleDto;
import com.wherego.wheregoserver.mapper.ArticleMapper;
import com.wherego.wheregoserver.repository.ArticleRepository;
import com.wherego.wheregoserver.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public List<SimpleArticleDto> getAll() {
        return articleRepository.getAll()
                .stream()
                .map(articleMapper::toSimpleArticleDto)
                .collect(Collectors.toList());
    }
}
