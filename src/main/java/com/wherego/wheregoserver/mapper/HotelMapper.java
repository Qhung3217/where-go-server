package com.wherego.wheregoserver.mapper;

import com.wherego.wheregoserver.dto.hotel.SimpleHotelDto;
import com.wherego.wheregoserver.respository.entity.*;
import org.mapstruct.*;
import org.springframework.stereotype.Component;


import java.util.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, collectionMappingStrategy =
        CollectionMappingStrategy.ADDER_PREFERRED)
@Component
public interface HotelMapper {

    @Mapping(target="districtName", source="district.name")
    @Mapping(target="roomFeatures", source="roomFeatures", qualifiedByName = "getRoomFeatureNames")
    @Mapping(target="roomTypes", source="roomTypes", qualifiedByName = "getRoomTypeNames")
    @Mapping(target="propertyAmenities", source="propertyAmenities", qualifiedByName=
            "getPropertyAmenityNames")
    @Mapping(target="averageRating", source="reviews", qualifiedByName="getAverageRating")
    @Mapping(target="totalRating", source="reviews", qualifiedByName="getTotalRating")
    SimpleHotelDto toSimpleHotelDto(Hotel hotel);

    @Named("getAverageRating")
    public static Float getAverageRating(Set<HotelReview> hotelReviews){
        List<Integer> ratings = new ArrayList<>();
        for(HotelReview review : hotelReviews){
            ratings.add(review.getRating());
        }
        OptionalDouble average = ratings.stream().mapToDouble(rating -> rating).average();
        return (float) average.orElse(0);
    }

    @Named("getTotalRating")
    public static Integer getTotalRating(Set<HotelReview> hotelReviews){
        return hotelReviews.size();
    }

    @Named("getRoomFeatureNames")
    public static List<String> getRoomFeatureName(Set<RoomFeature> roomFeatures){
        List<String> roomFeatureNames = new ArrayList<>();
        for (RoomFeature roomFeature : roomFeatures){
            roomFeatureNames.add(roomFeature.getFeature());
        }
        return roomFeatureNames;
    }

    @Named("getRoomTypeNames")
    public static List<String> getRoomTypeNames(Set<RoomType> roomTypes){
        List<String> roomTypeNames = new ArrayList<>();
        for (RoomType roomType : roomTypes){
            roomTypeNames.add(roomType.getType());
        }
        return roomTypeNames;
    }

    @Named("getPropertyAmenityNames")
    public static List<String> getPropertyAmenityNames(Set<PropertyAmenity> propertyAmenities){
        List<String> propertyAmenityNames = new ArrayList<>();
        for(PropertyAmenity propertyAmenity : propertyAmenities){
            propertyAmenityNames.add(propertyAmenity.getName());
        }
        return propertyAmenityNames;
    }
}
