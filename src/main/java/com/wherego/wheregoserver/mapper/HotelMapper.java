package com.wherego.wheregoserver.mapper;

import com.wherego.wheregoserver.dto.hotel.DetailHotelDto;
import com.wherego.wheregoserver.dto.hotel.SimpleHotelDto;
import com.wherego.wheregoserver.dto.review.ReviewDetailDto;
import com.wherego.wheregoserver.repository.entity.*;
import com.wherego.wheregoserver.utils.MapperUtils;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

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

    @Mapping(target="districtName", source="district.name")
    @Mapping(target="roomFeatures", source="roomFeatures", qualifiedByName = "getRoomFeatureNames")
    @Mapping(target="roomTypes", source="roomTypes", qualifiedByName = "getRoomTypeNames")
    @Mapping(target="propertyAmenities", source="propertyAmenities", qualifiedByName=
            "getPropertyAmenityNames")
    @Mapping(target="averageRating", source="reviews", qualifiedByName="getAverageRating")
    @Mapping(target="totalRating", source="reviews", qualifiedByName="getTotalRating")
    @Mapping(target="galleries", source="galleries", qualifiedByName="getGalleries")
    @Mapping(target="reviews", source="reviews", qualifiedByName="getReviews")
    DetailHotelDto toDetailHotelDto(Hotel hotel);

    @Named("getAverageRating")
    static Float getAverageRating(Set<HotelReview> hotelReviews){
        return MapperUtils.arrayToFloatAverage(hotelReviews, "getRating");
    }

    @Named("getTotalRating")
    static Integer getTotalRating(Set<HotelReview> hotelReviews){
        return hotelReviews.size();
    }

    @Named("getRoomFeatureNames")
    static List<String> getRoomFeatureName(Set<RoomFeature> roomFeatures){
        return MapperUtils.arrayToListString(roomFeatures, "getFeature");
    }

    @Named("getRoomTypeNames")
    static List<String> getRoomTypeNames(Set<RoomType> roomTypes){
        return MapperUtils.arrayToListString(roomTypes, "getType");
    }

    @Named("getPropertyAmenityNames")
    static List<String> getPropertyAmenityNames(Set<PropertyAmenity> propertyAmenities){
        return MapperUtils.arrayToListString(propertyAmenities, "getName");
    }

    @Named("getGalleries")
    static List<String> getGalleries(Set<HotelGallery> hotelGalleries){
        return MapperUtils.arrayToListString(hotelGalleries, "getImage");
    }

    @Named("getReviews")
    static Set<ReviewDetailDto> getReviews(Set<HotelReview> reviews){
        return MapperUtils.objectReviewToReviewDetail(reviews);
    }
}
