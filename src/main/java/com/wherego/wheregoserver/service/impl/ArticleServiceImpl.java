package com.wherego.wheregoserver.service.impl;

import com.wherego.wheregoserver.dto.ResponseMessageDto;
import com.wherego.wheregoserver.dto.article.CreateArticleDto;
import com.wherego.wheregoserver.dto.article.SimpleArticleDto;
import com.wherego.wheregoserver.mapper.ArticleMapper;
import com.wherego.wheregoserver.repository.ArticleRepository;
import com.wherego.wheregoserver.repository.WriterRepository;
import com.wherego.wheregoserver.repository.entity.Article;
import com.wherego.wheregoserver.repository.entity.Writer;
import com.wherego.wheregoserver.service.ArticleService;
import com.wherego.wheregoserver.service.JwtService;
import com.wherego.wheregoserver.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private WriterRepository writerRepository;
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private JwtService jwtService;

    @Override
    public List<SimpleArticleDto> getAll() {
        return articleRepository.getAll()
                .stream()
                .map(articleMapper::toSimpleArticleDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SimpleArticleDto> getRandom(Integer quantity) {
        return articleRepository.getRandom(quantity)
                .stream()
                .map(articleMapper::toSimpleArticleDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SimpleArticleDto> search(String keyword) {
        return articleRepository.search(keyword)
                .stream()
                .map(articleMapper::toSimpleArticleDto)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseMessageDto create(String token, CreateArticleDto createArticleDto) {
        try {
            Article article = articleMapper.toArticle(createArticleDto);
            article.setCreateDate(new Date());

            Writer writer = writerRepository.getByEmail(jwtService.extractUsername(token));
            article.setWriter(writer);

            String fileName = FileUtils.generateUniqueFilename(createArticleDto.getThumbnail());
            article.setThumbnail(fileName);

            articleRepository.create(article);

            FileUtils.uploadFile(createArticleDto.getThumbnail(), fileName);

            return ResponseMessageDto
                    .builder()
                    .message("Create article successfully")
                    .status(HttpStatus.CREATED)
                    .build();
        } catch (IOException e) {
            return ResponseMessageDto
                    .builder()
                    .message("Error when trying to upload file")
                    .status(HttpStatus.NOT_IMPLEMENTED)
                    .build();
        } catch (ParseException e) {
            return ResponseMessageDto
                    .builder()
                    .message("Incorrect date format")
                    .status(HttpStatus.PRECONDITION_FAILED)
                    .build();
        } catch (Exception e) {
            if (e.getMessage() == null) {
                return ResponseMessageDto
                        .builder()
                        .message("Not enough required fields")
                        .status(HttpStatus.BAD_REQUEST)
                        .build();
            } else {
                return ResponseMessageDto
                        .builder()
                        .message(e.getMessage())
                        .status(HttpStatus.CONFLICT)
                        .build();
            }
        }
    }
}
