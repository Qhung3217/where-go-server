package com.wherego.wheregoserver.respository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "hotel")
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

}
