package com.wherego.wheregoserver.repository;

import com.wherego.wheregoserver.exception.ResourceNotFoundException;
import com.wherego.wheregoserver.repository.entity.Place;
import com.wherego.wheregoserver.repository.entity.PlaceType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlaceRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Place> getAll() {
        TypedQuery<Place> query = em.createNamedQuery("select.All.Place", Place.class);
        return query.getResultList();
    }

    public List<Place> getRandom(Integer quantity) {
        if (quantity == null)
            quantity = 20;
        TypedQuery<Place> query = em.createNamedQuery("select.Random.Place", Place.class);
        query.setParameter("quantity", quantity);
        return query.getResultList();
    }

    public List<Place> search(String key) {
        if (key == null)
            return List.of();

        try {
            TypedQuery<Place> query = em.createNamedQuery("search.Place", Place.class);
            query.setParameter("keyword", "%" +key+"%");
            return query.getResultList();
        } catch (NoResultException e) {
            return List.of();
        }

    }

    public Place getById(Long id){
        Place result = em.find(Place.class,id);
        if (result == null)
            throw new ResourceNotFoundException("Place", "id", id);
        return result;
    }

    public List<PlaceType> getPlaceTypes() {
        TypedQuery<PlaceType> query = em.createNamedQuery("select.All.PlaceType", PlaceType.class);
        return query.getResultList();
    }

}
