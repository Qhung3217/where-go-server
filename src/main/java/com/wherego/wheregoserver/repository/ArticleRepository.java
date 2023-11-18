package com.wherego.wheregoserver.repository;

import com.wherego.wheregoserver.repository.entity.Article;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArticleRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Article> getAll(){
        TypedQuery<Article> query = em.createNamedQuery("select.All.Article", Article.class);
        return query.getResultList();
    }
}
