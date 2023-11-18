package com.wherego.wheregoserver.boundary;

import com.wherego.wheregoserver.dto.ResponseMessageDto;
import com.wherego.wheregoserver.dto.article.CreateArticleDto;
import com.wherego.wheregoserver.dto.article.SimpleArticleDto;
import com.wherego.wheregoserver.service.ArticleService;
import com.wherego.wheregoserver.utils.HeaderUtils;
import jakarta.transaction.Transactional;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleResource {
    @Autowired
    private ArticleService articleService;

    @GetMapping
    public ResponseEntity<List<SimpleArticleDto>> getAll() {
        return ResponseEntity.ok(articleService.getAll());
    }

    @GetMapping("/random")
    public ResponseEntity<List<SimpleArticleDto>> getRandom(@PathParam("quantity") Integer quantity) {
        return ResponseEntity.ok(articleService.getRandom(quantity));
    }

    @PostMapping("/create")
    @Transactional
    public ResponseEntity<ResponseMessageDto> create(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestParam(value = "title") String title,
            @RequestParam(value = "shortDescription") String shortDescription,
            @RequestParam(value = "content") String content,
            @RequestParam(value = "thumbnail") MultipartFile thumbnail
    ) {
        String token = HeaderUtils.getToken(authorizationHeader);
        CreateArticleDto article = CreateArticleDto
                .builder()
                .thumbnail(thumbnail)
                .shortDescription(shortDescription)
                .title(title)
                .content(content)
                .build();
        return new ResponseEntity<ResponseMessageDto>(
                articleService.create(
                        token,
                        article
                ),
                HttpStatus.CREATED
        );
    }
}
