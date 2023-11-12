package com.wherego.wheregoserver.dto.place;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NotBlank
public class SimplePlaceDto {
    private Long id;
    private String name;
    private String thumbnail;
    private String districtName;
    private List<String> types;
    private Float averageRating;
    private int totalRating;
}
