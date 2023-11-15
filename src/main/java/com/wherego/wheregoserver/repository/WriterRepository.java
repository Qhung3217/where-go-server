package com.wherego.wheregoserver.repository;

import com.wherego.wheregoserver.exception.UserNotFoundException;
import com.wherego.wheregoserver.repository.entity.Writer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.text.ParseException;

@Repository
public class WriterRepository {

    @PersistenceContext
    private EntityManager em;

    public Writer getByUsername(String username) throws UserNotFoundException{
        try {
            TypedQuery<Writer> query = em.createNamedQuery("select.Username.Writer", Writer.class);
            query.setParameter("username", username);
            Writer writer = query.getSingleResult();
            System.out.println("get by username: " + writer);
           return writer;

        } catch (UserNotFoundException e) {
            throw new UserNotFoundException("User");
        }
    }
    public Writer getByEmail(String email) throws UserNotFoundException{
        Writer writer = em.find(Writer.class, email);
        if (writer == null)
            throw new UserNotFoundException("Writer");
        return writer;
    }

    public void create(Writer writer) throws IOException, ParseException, NullPointerException, Exception{
        em.persist(writer);
    }
}
