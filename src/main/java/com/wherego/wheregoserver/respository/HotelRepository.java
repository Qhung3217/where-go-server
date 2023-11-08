package com.wherego.wheregoserver.respository;

import com.wherego.wheregoserver.respository.entity.PropertyAmenity;
import com.wherego.wheregoserver.respository.entity.RoomFeature;
import com.wherego.wheregoserver.respository.entity.RoomType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HotelRepository {

    @PersistenceContext
    private EntityManager em;

    public List<RoomFeature> getRoomFeatures() {
        TypedQuery<RoomFeature> query = em.createNamedQuery("select" +
                ".All.RoomFeatures", RoomFeature.class);
        return query.getResultList();
    }

    public List<RoomType> getRoomTypes() {
        TypedQuery<RoomType> query = em.createNamedQuery("select" +
                ".All.RoomTypes", RoomType.class);
        return query.getResultList();
    }

    public List<PropertyAmenity> getPropertyAmenities() {
        TypedQuery<PropertyAmenity> query = em.createNamedQuery(
                "select.All.PropertyAmenity", PropertyAmenity.class);
        return query.getResultList();
    }
}
