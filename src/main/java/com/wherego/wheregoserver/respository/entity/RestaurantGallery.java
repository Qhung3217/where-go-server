package com.wherego.wheregoserver.respository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class RestaurantGallery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "res_gallery_id")
    private Long id;

    @Column(name = "res_gallery_image", nullable = false)
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", referencedColumnName = "restaurant_id", nullable = false)
    private Restaurant restaurant;
}
