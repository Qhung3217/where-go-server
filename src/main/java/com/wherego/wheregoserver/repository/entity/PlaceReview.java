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
@Table(name = "place_review")
public class PlaceReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_review_id")
    private Long id;

    @Column(name = "place_review_comment", nullable = false, length=1000)
    private String comment;

    @Column(name = "place_review_rating", nullable = false)
    private int rating;

    @ManyToOne()
    @JoinColumn(name = "place_id", nullable = false)
    @JsonIgnore
    private Place place;

    @ManyToOne()
    @JoinColumn(name = "traveler_id", nullable = false)
    @JsonIgnore
    private Traveler traveler;
}
