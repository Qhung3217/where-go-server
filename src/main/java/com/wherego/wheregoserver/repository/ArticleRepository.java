package com.wherego.wheregoserver.repository;

import com.wherego.wheregoserver.repository.entity.Article;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public class ArticleRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Article> getAll(){
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
}
