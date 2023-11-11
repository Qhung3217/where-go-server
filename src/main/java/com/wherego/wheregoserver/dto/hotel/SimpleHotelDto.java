package com.wherego.wheregoserver.dto.hotel;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NotBlank
public class SimpleHotelDto {
    private Long id;
    private String name;
    private String address;
    private String hotelClass;
    private String thumbnail;
    private Long price;
    private String districtName;
    private List<String> roomFeatures;
    private List<String> roomTypes;
    private List<String> propertyAmenities;
    private Float averageRating;
    private int totalRating;
}
