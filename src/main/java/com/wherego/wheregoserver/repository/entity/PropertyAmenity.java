package com.wherego.wheregoserver.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "property_amenity")
@NamedQueries({
        @NamedQuery(name="select.All.PropertyAmenity", query =
                "SELECT pa FROM PropertyAmenity pa")
})
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

    @ManyToMany(mappedBy = "propertyAmenities")
    @JsonIgnore
    private Set<Hotel> hotels;

}
