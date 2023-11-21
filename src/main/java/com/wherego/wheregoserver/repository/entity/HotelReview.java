package com.wherego.wheregoserver.repository.entity;

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
@Table(name = "hotel_review")
public class HotelReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_review_id")
    private Long id;

    @Column(name = "hotel_review_comment", nullable = false, length=1000)
    private String comment;

    @Column(name = "hotel_review_rating", nullable = false)
    private int rating;

    @ManyToOne()
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @ManyToOne()
    @JoinColumn(name = "traveler_id", nullable = false)
    private Traveler traveler;
}
