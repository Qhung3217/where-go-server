package com.wherego.wheregoserver.dto.hotel;

import com.wherego.wheregoserver.dto.ReviewDetailDto;
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
@NotBlank
@NoArgsConstructor
public class DetailHotelDto {
    private Long id;
    private String name;
    private String address;
    private String hotelClass;
    private String description;
    private String thumbnail;
    private Long price;
    private String districtName;
    private List<String> galleries;
    private List<String> roomFeatures;
    private List<String> roomTypes;
    private List<String> propertyAmenities;
    private Set<ReviewDetailDto> reviews;
    private Float averageRating;
    private int totalRating;
}
