package com.wherego.wheregoserver.repository;

import com.wherego.wheregoserver.repository.entity.Booking;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class BookingRepository {
    @PersistenceContext
    private EntityManager em;

    public void create(Booking booking) throws Exception {
        em.persist(booking);
    }
}
