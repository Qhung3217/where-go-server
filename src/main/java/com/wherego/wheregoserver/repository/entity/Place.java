package com.wherego.wheregoserver.repository.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "place")
@NamedQueries({
        @NamedQuery(name="select.All.Place", query="SELECT p FROM Place p"),
})
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
