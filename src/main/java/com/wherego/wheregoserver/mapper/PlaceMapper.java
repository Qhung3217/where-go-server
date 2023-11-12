package com.wherego.wheregoserver.mapper;

import com.wherego.wheregoserver.dto.place.SimplePlaceDto;
import com.wherego.wheregoserver.repository.entity.Place;
import com.wherego.wheregoserver.repository.entity.PlaceReview;
import com.wherego.wheregoserver.repository.entity.PlaceType;
import com.wherego.wheregoserver.utils.MapperUtils;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface PlaceMapper {

    @Mapping(target="districtName", source="district.name")
    @Mapping(target="types", source="types", qualifiedByName = "getTypeNames")
    @Mapping(target="averageRating", source="reviews", qualifiedByName = "getAverageRating")
    @Mapping(target="totalRating", source="reviews", qualifiedByName ="getTotalRating")
    public SimplePlaceDto toSimplePlaceDto(Place place);

    @Named("getTypeNames")
    public static List<String> getTypeNames(Set<PlaceType> types){
        return MapperUtils.arrayToListString(types, "getType");
    }

    @Named("getAverageRating")
    public static Float getAverageRating(Set<PlaceReview> reviews){
        return MapperUtils.arrayToFloatAverage(reviews, "getRating");
    }

    @Named("getTotalRating")
    public static int getTotalRating(Set<PlaceReview> reviews){
        return reviews.size();
    }
}
