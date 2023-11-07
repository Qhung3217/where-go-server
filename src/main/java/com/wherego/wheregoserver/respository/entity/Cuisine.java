package com.wherego.wheregoserver.respository.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cuisine")
public class Cuisine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cuisine_id")
    private Long id;

    @Column(name = "cuisine_type", nullable = false)
    private String type;

    public Cuisine(Long id, String type) {
        this.id = id;
        this.type = type;
    }

    @ManyToMany(mappedBy = "cuisines")
    private Set<Restaurant> restaurant = new HashSet<>();

}
