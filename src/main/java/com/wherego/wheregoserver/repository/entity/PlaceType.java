package com.wherego.wheregoserver.repository.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "place_type")
@NamedQuery(name="select.All.PlaceType", query="SELECT pt FROM PlaceType pt")
public class PlaceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_type_id")
    private Long id;

    @Column(name = "place_type_type", nullable = false)
    private String type;

    public PlaceType(Long id, String type) {
        this.id = id;
        this.type = type;
    }

    @ManyToMany(mappedBy = "types")
    @JsonIgnore
    private Set<Place> places;

}