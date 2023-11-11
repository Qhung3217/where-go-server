package com.wherego.wheregoserver.repository.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private Long id;

    @Column(name = "restaurant_name", nullable = false)
    private String name;

    @Column(name = "restaurant_address", nullable = false)
    private String address;

    @Column(name = "restaurant_thumbnail", nullable = false, length=512)
    private String thumbnail;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "district_id", nullable = false)
    private District district;

    public Restaurant(Long id, String name, String address, String thumbnail, District district) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.thumbnail = thumbnail;
        this.district = district;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "restaurant_cuisine", joinColumns = @JoinColumn(name = "restaurant_id"), inverseJoinColumns = @JoinColumn(name = "cuisine_id"))
    private Set<Cuisine> cuisines = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "restaurant_feature", joinColumns = @JoinColumn(name = "restaurant_id"), inverseJoinColumns = @JoinColumn(name = "feature_id"))
    private Set<Feature> features = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "restaurant_meal", joinColumns = @JoinColumn(name = "restaurant_id"), inverseJoinColumns = @JoinColumn(name = "meal_id"))
    private Set<Meal> meals = new HashSet<>();

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<RestaurantGallery> galleries;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private Set<RestaurantReview> reviews;

}
