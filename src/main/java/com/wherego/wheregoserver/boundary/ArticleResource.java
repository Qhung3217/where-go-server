package com.wherego.wheregoserver.boundary;

import com.wherego.wheregoserver.dto.article.SimpleArticleDto;
import com.wherego.wheregoserver.service.ArticleService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleResource {
    @Autowired
    private ArticleService articleService;

    @GetMapping
    public ResponseEntity<List<SimpleArticleDto>> getAll(){
        return ResponseEntity.ok(articleService.getAll());
    }
    @GetMapping("/random")
    public ResponseEntity<List<SimpleArticleDto>> getRandom(@PathParam("quantity") Integer quantity){
        return ResponseEntity.ok(articleService.getRandom(quantity));
    }
}
