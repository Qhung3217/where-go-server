package com.wherego.wheregoserver.respository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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

    @Column(name = "place_review_comment", nullable = false)
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
