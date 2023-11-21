package com.wherego.wheregoserver.dto.place;

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
@AllArgsConstructor
@NoArgsConstructor
@NotBlank
public class DetailPlaceDto {
    private Long id;
    private String name;
    private String thumbnail;
    private String districtName;
    private List<String> types;
    private List<String> galleries;
    private Set<ReviewDetailDto> reviews;
    private Float averageRating;
    private int totalRating;
}
