package com.wherego.wheregoserver.dto.restaurant;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NotBlank
public class SimpleRestaurantDto {
    private Long id;
    private String name;
    private String address;
    private String thumbnail;
    private String districtName;
    private List<String> cuisines;
    private List<String> features;
    private List<String> meals;
    private Float averageRating;
    private int totalRating;
}
