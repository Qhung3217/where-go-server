package com.wherego.wheregoserver.repository;

import com.wherego.wheregoserver.repository.entity.Article;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.text.ParseException;
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

    public void create(Article article) throws IOException, ParseException, NullPointerException, Exception {
        em.persist(article);
    }

    public List<Article> search(String keyword){
        if (keyword.isBlank())
            return List.of();
        try {
            TypedQuery<Article> query = em.createNamedQuery("search.Article", Article.class);
            query.setParameter("keyword", "%"+keyword+"%");
            return query.getResultList();
        } catch (NoResultException e) {
            return List.of();
        }
    }
}
