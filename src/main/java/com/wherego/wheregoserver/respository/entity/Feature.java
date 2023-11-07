package com.wherego.wheregoserver.respository.entity;

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
@Table(name = "feature")
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
    private Set<Restaurant> restaurant;

}
