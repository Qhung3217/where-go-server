package com.wherego.wheregoserver.repository;

import com.wherego.wheregoserver.repository.entity.HotelReview;
import com.wherego.wheregoserver.repository.entity.PlaceReview;
import com.wherego.wheregoserver.repository.entity.RestaurantReview;
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

    public void createRestaurantReview(RestaurantReview restaurantReview) {
        em.persist(restaurantReview);
    }

    public void createPlaceReview(PlaceReview review) {
        em.persist(review);
    }
}
