package com.wherego.wheregoserver.repository.entity;

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
@Table(name = "meal")
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meal_id")
    private Long id;

    @Column(name = "meal_type", nullable = false)
    private String type;

    public Meal(Long id, String type) {
        this.id = id;
        this.type = type;
    }

    @ManyToMany(mappedBy = "meals")
    private Set<Restaurant> restaurant;

}
