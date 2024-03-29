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
@Table(name = "room_feature")
@NamedQueries({
        @NamedQuery(name = "select.All.RoomFeatures", query="SELECT" +
                " rf FROM RoomFeature rf")
})
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
    @JsonIgnore
    private Set<Hotel> hotels;

}
