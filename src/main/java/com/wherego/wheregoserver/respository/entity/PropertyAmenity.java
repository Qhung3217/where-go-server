package com.wherego.wheregoserver.respository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "property_amenity")
public class PropertyAmenity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "property_amenity_id")
    private Long id;

    @Column(name = "property_amenity_name", nullable = false)
    private String name;

    public PropertyAmenity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
