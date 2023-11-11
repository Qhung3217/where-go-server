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
@Table(name = "feature")
@NamedQueries({
        @NamedQuery(name = "select.All.Feature", query = "SELECT f FROM Feature f")
})
public class Feature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feature_id")
    private Long id;

    @Column(name = "feature_name", nullable = false)
    private String name;

    public Feature(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @ManyToMany(mappedBy = "features")
    @JsonIgnore
    private Set<Restaurant> restaurant;

}
