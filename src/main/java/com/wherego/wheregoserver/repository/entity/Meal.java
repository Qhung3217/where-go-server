package com.wherego.wheregoserver.repository.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "meal")
@NamedQueries({
        @NamedQuery(name = "select.All.Meal", query = "SELECT m FROM Meal m")
})
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
    @JsonIgnore
    private Set<Restaurant> restaurant;

}
