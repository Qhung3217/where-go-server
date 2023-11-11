package com.wherego.wheregoserver.repository.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "district")
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "district_id")
    private Long id;

    @Column(name = "district_name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    public District(Long id, String name, City city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    @OneToMany(mappedBy = "district", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Restaurant> restaurants;

    @OneToMany(mappedBy = "district", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Place> places;

    @OneToMany(mappedBy = "district", fetch = FetchType.LAZY, cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Hotel> hotels;

}