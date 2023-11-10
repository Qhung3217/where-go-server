
package com.wherego.wheregoserver.respository;

import com.wherego.wheregoserver.respository.entity.Hotel;
import com.wherego.wheregoserver.respository.entity.PropertyAmenity;
import com.wherego.wheregoserver.respository.entity.RoomFeature;
import com.wherego.wheregoserver.respository.entity.RoomType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HotelRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Hotel> search(String key){
        if (key.isBlank())
            return List.of();
        try {

        TypedQuery<Hotel> query = em.createNamedQuery("search.Hotel", Hotel.class);
        query.setParameter("keyword", "%"+key+"%");
        return query.getResultList();
        }catch  (NoResultException exception){
            return List.of();
        }
    }
    public List<Hotel> getAll() {
        TypedQuery<Hotel> query = em.createNamedQuery("select.All.Hotel", Hotel.class);
        return query.getResultList();
        /*
        -- Lets DTO call what fields are needed
        List<Hotel> result = query.getResultList();
        for (Hotel hotel : result) {
            Hibernate.initialize(hotel.getPropertyAmenities());
            Hibernate.initialize(hotel.getRoomFeatures());
            Hibernate.initialize(hotel.getRoomTypes());
            Hibernate.initialize(hotel.getReviews());
            Hibernate.initialize(hotel.getGalleries());
            Hibernate.initialize(hotel.getBookings());
        }
        return result;*/
    }

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
