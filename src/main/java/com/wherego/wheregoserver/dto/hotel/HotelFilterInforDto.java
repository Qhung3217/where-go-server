package com.wherego.wheregoserver.dto.hotel;

import com.wherego.wheregoserver.respository.entity.PropertyAmenity;
import com.wherego.wheregoserver.respository.entity.RoomFeature;
import com.wherego.wheregoserver.respository.entity.RoomType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NotBlank
public class HotelFilterInforDto {
    private List<RoomType> roomTypes;
    private List<RoomFeature> roomFeatures;
    private List<PropertyAmenity> propertyAmenities;
}
