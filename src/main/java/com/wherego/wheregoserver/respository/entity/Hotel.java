package com.wherego.wheregoserver.respository.entity;

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
@Table(name = "hotel")
@NamedQueries({
@NamedQuery(name="select.All.Hotel", query="SELECT h FROM Hotel h")
})
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_id")
    private Long id;

    @Column(name = "hotel_name", nullable = false)
    private String name;

    @Column(name = "hotel_address", nullable = false)
    private String address;

    @Column(name = "hotel_class", nullable = false)
    private String hotelClass;

    @Column(name = "hotel_description", nullable = false)
    private String description;

    @Column(name = "hotel_thumbnail", nullable = false)
    private String thumbnail;

    @Column(name = "hotel_price", nullable = false)
    private Long price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "district_id", nullable = false)
    private District district;

    public Hotel(Long id, String name, String address, String hotelClass, String description, String thumbnail,
            Long price, District district) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.hotelClass = hotelClass;
        this.description = description;
        this.thumbnail = thumbnail;
        this.price = price;
        this.district = district;
    }

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<HotelGallery> galleries;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "hotel_room_feature", joinColumns = @JoinColumn(name = "hotel_id"), inverseJoinColumns = @JoinColumn(name = "room_feature_id"))
    private Set<RoomFeature> roomFeatures = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "hotel_room_type", joinColumns = @JoinColumn(name = "hotel_id"), inverseJoinColumns = @JoinColumn(name = "room_type_id"))
    private Set<RoomType> roomTypes = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "hotel_property_amenity", joinColumns = @JoinColumn(name = "hotel_id"), inverseJoinColumns = @JoinColumn(name = "property_amenity_id"))
    private Set<PropertyAmenity> propertyAmenities = new HashSet<PropertyAmenity>();

    @OneToMany(mappedBy = "hotel",cascade = CascadeType.ALL)
    private Set<HotelReview> reviews;

}
