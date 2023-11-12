package com.wherego.wheregoserver.mapper;

import com.wherego.wheregoserver.dto.hotel.DetailHotelDto;
import com.wherego.wheregoserver.dto.hotel.SimpleHotelDto;
import com.wherego.wheregoserver.repository.entity.*;
import com.wherego.wheregoserver.utils.MapperUtils;
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

    @Mapping(target="district", source="district.name")
    @Mapping(target="roomFeatures", source="roomFeatures", qualifiedByName = "getRoomFeatureNames")
    @Mapping(target="roomTypes", source="roomTypes", qualifiedByName = "getRoomTypeNames")
    @Mapping(target="propertyAmenities", source="propertyAmenities", qualifiedByName=
            "getPropertyAmenityNames")
    @Mapping(target="averageRating", source="reviews", qualifiedByName="getAverageRating")
    @Mapping(target="totalRating", source="reviews", qualifiedByName="getTotalRating")
    @Mapping(target="galleries", source="galleries", qualifiedByName="getGalleries")
    DetailHotelDto toDetailHotelDto(Hotel hotel);

    @Named("getAverageRating")
    public static Float getAverageRating(Set<HotelReview> hotelReviews){
        return MapperUtils.arrayToFloatAverage(hotelReviews, "getRating");
    }

    @Named("getTotalRating")
    public static Integer getTotalRating(Set<HotelReview> hotelReviews){
        return hotelReviews.size();
    }

    @Named("getRoomFeatureNames")
    public static List<String> getRoomFeatureName(Set<RoomFeature> roomFeatures){
        return MapperUtils.arrayToListString(roomFeatures, "getFeature");
    }

    @Named("getRoomTypeNames")
    public static List<String> getRoomTypeNames(Set<RoomType> roomTypes){
        return MapperUtils.arrayToListString(roomTypes, "getType");
    }

    @Named("getPropertyAmenityNames")
    public static List<String> getPropertyAmenityNames(Set<PropertyAmenity> propertyAmenities){
        return MapperUtils.arrayToListString(propertyAmenities, "getName");
    }

    @Named("getGalleries")
    public static List<String> getGalleries(Set<HotelGallery> hotelGalleries){
        return MapperUtils.arrayToListString(hotelGalleries, "getImage");
    }
}
