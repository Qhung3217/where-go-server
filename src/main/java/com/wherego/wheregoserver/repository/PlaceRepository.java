package com.wherego.wheregoserver.repository;

import com.wherego.wheregoserver.repository.entity.Place;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlaceRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Place> getAll(){
        TypedQuery<Place> query = em.createNamedQuery("select.All.Place", Place.class);
        return query.getResultList();
    }
}
