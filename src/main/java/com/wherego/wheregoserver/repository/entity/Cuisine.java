package com.wherego.wheregoserver.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cuisine")
@NamedQueries({
        @NamedQuery(name = "select.All.Cuisine", query = "SELECT c FROM Cuisine c")
})
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
    @JsonIgnore
    private Set<Restaurant> restaurant = new HashSet<>();

}
