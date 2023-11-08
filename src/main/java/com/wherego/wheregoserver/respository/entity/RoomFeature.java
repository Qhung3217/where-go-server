package com.wherego.wheregoserver.respository.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "room_feature")
public class RoomFeature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_feature_id")
    private Long id;

    @Column(name = "room_feature_feature", nullable = false)
    private String feature;

    public RoomFeature(Long id, String feature) {
        this.id = id;
        this.feature = feature;
    }

    @ManyToMany(mappedBy = "roomFeatures")
    private Set<Hotel> hotels;

}
