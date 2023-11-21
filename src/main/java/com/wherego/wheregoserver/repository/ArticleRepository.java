package com.wherego.wheregoserver.repository;

import com.wherego.wheregoserver.exception.ResourceNotFoundException;
import com.wherego.wheregoserver.repository.entity.Article;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Repository
public class ArticleRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Article> getAll() {
        TypedQuery<Article> query = em.createNamedQuery("select.All.Article", Article.class);
        return query.getResultList();
    }

    public List<Article> getRandom(Integer quantity) {
        if (quantity == null)
            quantity = 20;
        TypedQuery<Article> query = em.createNamedQuery("select.Random.Article", Article.class);
        query.setParameter("quantity", quantity);
        return query.getResultList();
    }

    public Article getById(Long id) {
        Article article = em.find(Article.class, id);
        if (article == null)
            throw new ResourceNotFoundException("Article", "id", id);
        return article;
    }

    public void create(Article article)
            throws Exception {
        em.persist(article);
    }

    public void update(Article article)
            throws Exception {
        em.merge(article);
    }

    public List<Article> search(String keyword) {
        if (keyword.isBlank())
            return List.of();
        try {
            TypedQuery<Article> query = em.createNamedQuery("search.Article", Article.class);
            query.setParameter("keyword", "%" + keyword + "%");
            return query.getResultList();
        } catch (NoResultException e) {
            return List.of();
        }
    }
}
