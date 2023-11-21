package com.wherego.wheregoserver.repository;

import com.wherego.wheregoserver.repository.entity.HotelReview;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewRepository {
    @PersistenceContext
    private EntityManager em;

    public void createHotelReview(HotelReview hotelReview) {
        em.persist(hotelReview);
    }
}
