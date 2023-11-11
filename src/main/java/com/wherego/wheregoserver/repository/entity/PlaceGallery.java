package com.wherego.wheregoserver.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "place_gallery")
public class PlaceGallery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_gallery_id")
    private Long id;

    @Column(name = "place_gallery_image", nullable = false, length=512)
    private String image;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "place_id", nullable = false)
    private Place place;

}
