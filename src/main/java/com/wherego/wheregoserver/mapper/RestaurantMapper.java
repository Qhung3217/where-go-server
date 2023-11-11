package com.wherego.wheregoserver.mapper;

import com.wherego.wheregoserver.dto.restaurant.SimpleRestaurantDto;
import com.wherego.wheregoserver.repository.entity.*;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, collectionMappingStrategy =
        CollectionMappingStrategy.ADDER_PREFERRED)
@Component
public interface RestaurantMapper  {

    @Mapping(target="districtName", source="district.name")
    @Mapping(target="cuisines", source="cuisines", qualifiedByName = "getCuisineNames")
    @Mapping(target="meals", source="meals", qualifiedByName = "getMealNames")
    @Mapping(target="features", source="features", qualifiedByName="getFeatureNames")
    @Mapping(target="averageRating", source="reviews", qualifiedByName="getAverageRating")
    @Mapping(target="totalRating", source="reviews", qualifiedByName="getTotalRating")
    public SimpleRestaurantDto toSimpleRestaurantDto(Restaurant restaurant);

    @Named("getCuisineNames")
    default List<String> getCuisineNames(Set<Cuisine> cuisines){
        return MapperUtils.arrayToListString(cuisines, "getType");
    }
    @Named("getMealNames")
    default List<String> getMealNames(Set<Meal> meals){
        return MapperUtils.arrayToListString(meals, "getType");
    }
    @Named("getFeatureNames")
    default List<String> getFeatureNames(Set<Feature> features){
        return MapperUtils.arrayToListString(features, "getName");
    }

    @Named("getAverageRating")
    default Float getAverageRating(Set<RestaurantReview> reviews){
        return MapperUtils.arrayToFloatAverage(reviews, "getRating");
    }

    @Named("getTotalRating")
    public static int getTotalRating(Set<RestaurantReview> reviews){
        return reviews.size();
    }
}
