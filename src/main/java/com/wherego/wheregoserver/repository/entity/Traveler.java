package com.wherego.wheregoserver.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wherego.wheregoserver.constant.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "traveler")
@NamedQueries({
        @NamedQuery(name = "select.Email.Traveler", query = "SELECT t FROM Traveler t WHERE t.email" +
                " = :email"),
        @NamedQuery(name = "select.Username.Traveler", query = "SELECT t FROM Traveler t WHERE t" +
                ".username = :username"),
})
public class Traveler {

    @Id
    @Column(name = "traveler_email")
    private String email;

    @Column(name = "traveler_name", nullable = false)
    private String name;

    @Column(name = "traveler_tels", nullable = false, length = 10)
    private String tels;

    @Column(name = "traveler_avatar", nullable = true, length = 512)
    private String avatar;

    @Column(name = "traveler_dob", nullable = false)
    private Date dob;

    @Column(name = "traveler_username", nullable = false)
    private String username;

    @Column(name = "traveler_password", nullable = false)
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
    @JsonIgnore
    private Set<HotelReview> hotelReviews;

    @OneToMany(mappedBy = "traveler", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<RestaurantReview> restaurantReviews;

    @OneToMany(mappedBy = "traveler", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<PlaceReview> placeReviews;

    @OneToMany(mappedBy = "traveler", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Booking> bookings;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(UserRole.ROLE_TRAVELER));
    }
}
