package com.wherego.wheregoserver.mapper;

import com.wherego.wheregoserver.dto.review.ReviewCreateDto;
import com.wherego.wheregoserver.repository.entity.HotelReview;
import com.wherego.wheregoserver.repository.entity.PlaceReview;
import com.wherego.wheregoserver.repository.entity.RestaurantReview;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Component
@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED
)
@MapperConfig(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ReviewMapper {
    HotelReview toHotelReview(ReviewCreateDto review);

    RestaurantReview toRestaurantReview(ReviewCreateDto review);

    PlaceReview toPlaceReview(ReviewCreateDto review);
}
