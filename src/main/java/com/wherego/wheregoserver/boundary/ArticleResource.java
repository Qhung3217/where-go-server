package com.wherego.wheregoserver.boundary;

import com.wherego.wheregoserver.constant.UserRole;
import com.wherego.wheregoserver.dto.ResponseMessageDto;
import com.wherego.wheregoserver.dto.article.CreateArticleDto;
import com.wherego.wheregoserver.dto.article.DetailArticleDto;
import com.wherego.wheregoserver.dto.article.SimpleArticleDto;
import com.wherego.wheregoserver.service.ArticleService;
import com.wherego.wheregoserver.utils.HeaderUtils;
import jakarta.annotation.security.PermitAll;
import jakarta.transaction.Transactional;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('"+ UserRole.WRITER+"')")
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

    @PostMapping("/update/{articleId}")
    @PreAuthorize("hasRole('"+ UserRole.WRITER+"')")
    @Transactional
    public ResponseEntity<ResponseMessageDto> update(
            @PathVariable Long articleId,
            @RequestHeader("Authorization") String authorizationHeader,
            @PathParam("title") String title,
            @PathParam("shortDescription") String shortDescription,
            @PathParam("content") String content,
            @PathParam("thumbnail") MultipartFile thumbnail
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
                articleService.update(
                        token,
                        article,
                        articleId
                ),
                HttpStatus.OK
        );
    }

    @GetMapping("/detail/{id}")
    @PermitAll
    public ResponseEntity<DetailArticleDto> getDetail(@PathVariable Long id) {
        return ResponseEntity.ok(articleService.getById(id));
    }
}
