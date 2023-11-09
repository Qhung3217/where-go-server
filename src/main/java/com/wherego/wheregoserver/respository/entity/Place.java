package com.wherego.wheregoserver.respository.entity;

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
@Table(name = "place")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_id")
    private Long id;

    @Column(name = "place_name", nullable = false)
    private String name;

    @Column(name = "place_thumbnail", nullable = false, length=512)
    private String thumbnail;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "district_id", nullable = false)
    private District district;

    public Place(Long id, String name, String thumbnail, District district) {
        this.id = id;
        this.name = name;
        this.thumbnail = thumbnail;
        this.district = district;
    }

    @OneToMany(mappedBy = "place", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<PlaceGallery> galleries;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "place_place_type", joinColumns = @JoinColumn(name = "place_id"), inverseJoinColumns = @JoinColumn(name = "place_type_id"))
    private Set<PlaceType> types = new HashSet<>();

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private Set<PlaceReview> reviews;

}
