package com.wherego.wheregoserver.repository;

import com.wherego.wheregoserver.repository.entity.Restaurant;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RestaurantRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Restaurant> getAll(){
        TypedQuery<Restaurant> query = em.createNamedQuery("select.All.Restaurant",
                Restaurant.class);
        return query.getResultList();
    }

    public List<Restaurant> getRandom(Integer quantity){
        if (quantity == null)
            quantity = 20;
        TypedQuery<Restaurant> query = em.createNamedQuery("select.Random.Restaurant",
                Restaurant.class);
        query.setParameter("quantity", quantity);
        return query.getResultList();
    }
}
