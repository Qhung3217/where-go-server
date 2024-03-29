package com.wherego.wheregoserver.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "restaurant_review")
public class RestaurantReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_review_id")
    private Long id;

    @Column(name = "restaurant_review_comment", nullable = false, length=800)
    private String comment;

    @Column(name = "restaurant_review_rating", nullable = false)
    private int rating;

    @ManyToOne()
    @JoinColumn(name = "restaurant_id", nullable = false)
    @JsonIgnore
    private Restaurant restaurant;

    @ManyToOne()
    @JoinColumn(name = "traveler_id", nullable = false)
    @JsonIgnore
    private Traveler traveler;
}
