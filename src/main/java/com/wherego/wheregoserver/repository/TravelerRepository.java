package com.wherego.wheregoserver.repository;

import com.wherego.wheregoserver.exception.UserNotFoundException;
import com.wherego.wheregoserver.repository.entity.Traveler;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.text.ParseException;

@Repository
public class TravelerRepository {

    @PersistenceContext
    private EntityManager em;

    public Traveler getByEmail(String email) {
        try {
            TypedQuery<Traveler> query = em.createNamedQuery("select.Email.Traveler", Traveler.class);
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new UserNotFoundException("User");
        }
    }

    ;

    public Traveler getByUsername(String username) {
        try {
            TypedQuery<Traveler> query = em.createNamedQuery(
                    "select.Username.Traveler",
                    Traveler.class
            );
            query.setParameter("username", username);
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new UserNotFoundException("User");
        }
    }

    public void create(Traveler traveler)
            throws IOException, ParseException, NullPointerException, Exception {
        em.persist(traveler);
    }


    public void update(Traveler traveler)
            throws IOException, ParseException, NullPointerException, Exception {
        em.merge(traveler);
    }
}
