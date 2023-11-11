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
@Table(name = "room_type")
@NamedQueries({
        @NamedQuery(name = "select.All.RoomTypes", query="SELECT rt" +
                " FROM RoomType rt")
})
public class RoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_type_id")
    private Long id;

    @Column(name = "room_type_type", nullable = false)
    private String type;

    public RoomType(Long id, String type) {
        this.id = id;
        this.type = type;
    }

    @ManyToMany(mappedBy = "roomTypes")
    @JsonIgnore
    private Set<Hotel> hotels;
}
