package com.wherego.wheregoserver.repository;

import com.wherego.wheregoserver.exception.ResourceNotFoundException;
import com.wherego.wheregoserver.repository.entity.Cuisine;
import com.wherego.wheregoserver.repository.entity.Feature;
import com.wherego.wheregoserver.repository.entity.Meal;
import com.wherego.wheregoserver.repository.entity.Restaurant;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RestaurantRepository {

    @PersistenceContext
    private EntityManager em;

    public Restaurant getById(Long id){
        Restaurant result = em.find(Restaurant.class, id);
        if(result == null)
            throw new ResourceNotFoundException("Restaurant", "id", id);
        return result;
    }

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

    public List<Restaurant> search(String key){
        if (key.isBlank())
            return List.of();
        try {
            TypedQuery<Restaurant> query = em.createNamedQuery("search.Restaurant",
                    Restaurant.class);
            query.setParameter("keyword", "%"+key+"%");
            return query.getResultList();
        }catch(NoResultException e){
            return List.of();
        }
    }

    public List<Cuisine> getCuisines(){
        TypedQuery<Cuisine> query = em.createNamedQuery("select.All.Cuisine", Cuisine.class);
        return query.getResultList();
    }
    public List<Meal> getMeals(){
        TypedQuery<Meal> query = em.createNamedQuery("select.All.Meal", Meal.class);
        return query.getResultList();
    }
    public List<Feature> getFeatures(){
        TypedQuery<Feature> query = em.createNamedQuery("select.All.Feature", Feature.class);
        return query.getResultList();
    }
}
