package com.wherego.wheregoserver.respository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "traveler")
public class Traveler {

    @Id
    @Column(name= "traveler_email")
    private String email;

    @Column(name= "traveler_name", nullable=false)
    private String name;

    @Column(name= "traveler_tels", nullable = false)
    private String tels;

    @Column(name= "traveler_avatar", nullable = true)
    private String avatar;

    @Column(name= "traveler_dob", nullable = false)
    private Date dob;

    @Column(name="traveler_username", nullable = false)
    private String username;

    @Column(name="traveler_password", nullable = false)
    private String password;

    public Traveler(String email, String name, String tels, String avatar, Date dob, String username, String password) {
        this.email = email;
        this.name = name;
        this.tels = tels;
        this.avatar = avatar;
        this.dob = dob;
        this.username = username;
        this.password = password;
    }

    @OneToMany(mappedBy = "traveler", cascade = CascadeType.ALL)
    private Set<HotelReview> hotelReviews;

    @OneToMany(mappedBy = "traveler", cascade = CascadeType.ALL)
    private Set<RestaurantReview> restaurantReviews;

    @OneToMany(mappedBy = "traveler", cascade = CascadeType.ALL)
    private Set<PlaceReview> placeReviews;

    @OneToMany(mappedBy = "traveler", cascade = CascadeType.ALL)
    private Set<Booking> bookings;
}
