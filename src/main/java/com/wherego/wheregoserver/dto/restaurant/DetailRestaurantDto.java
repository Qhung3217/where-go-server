package com.wherego.wheregoserver.dto.restaurant;

import com.wherego.wheregoserver.dto.review.ReviewDetailDto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NotBlank
public class DetailRestaurantDto {
    private Long id;
    private String name;
    private String address;
    private String thumbnail;
    private String districtName;
    private List<String> galleries;
    private List<String> cuisines;
    private List<String> features;
    private List<String> meals;
    private Set<ReviewDetailDto> reviews;
    private Float averageRating;
    private int totalRating;
}
