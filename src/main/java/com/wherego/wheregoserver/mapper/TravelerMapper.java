package com.wherego.wheregoserver.mapper;

import com.wherego.wheregoserver.dto.booking.SimpleBookingDto;
import com.wherego.wheregoserver.dto.review.SimpleReviewDto;
import com.wherego.wheregoserver.dto.traveler.DetailTravelerDto;
import com.wherego.wheregoserver.dto.traveler.TravelerRegisterDto;
import com.wherego.wheregoserver.repository.entity.*;
import com.wherego.wheregoserver.utils.MapperUtils;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED
)
@MapperConfig(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface TravelerMapper {

    Traveler toTraveler(TravelerRegisterDto register);

    @Mapping(target="hotelReviews", source="hotelReviews", qualifiedByName = "getSimpleHotelReview")
    @Mapping(target="placeReviews", source="placeReviews", qualifiedByName = "getSimplePlaceReview")
    @Mapping(target="restaurantReviews", source="restaurantReviews", qualifiedByName =
            "getSimpleRestaurantReview")
    @Mapping(target="bookings", source="bookings", qualifiedByName = "getSimpleBookingReview")
    DetailTravelerDto toDetailTravelerDto(Traveler traveler);

    @Named("getSimpleHotelReview")
    static Set<SimpleReviewDto> getSimpleHotelReview(Set<HotelReview> review){
       return MapperUtils.objectReviewToSimpleReview(review);
    }
    @Named("getSimplePlaceReview")
    static Set<SimpleReviewDto> getSimplePlaceReview(Set<PlaceReview> review){
        return MapperUtils.objectReviewToSimpleReview(review);
    }
    @Named("getSimpleRestaurantReview")
    static Set<SimpleReviewDto> getSimpleRestaurantReview(Set<RestaurantReview> review){
        return MapperUtils.objectReviewToSimpleReview(review);
    }
    @Named("getSimpleBookingReview")
    static Set<SimpleBookingDto> getSimpleBookingReview(Set<Booking> bookings){
        Set<SimpleBookingDto> result = new HashSet<>();
        for (Booking book : bookings){
            SimpleBookingDto simpleBooking = new SimpleBookingDto();
            simpleBooking.setBookDate(book.getBookDate());
            simpleBooking.setId(book.getId());
            simpleBooking.setPeople(book.getPeople());
            simpleBooking.setCheckin(book.getCheckin());
            simpleBooking.setCheckout(book.getCheckout());
            simpleBooking.setPrice(book.getPrice());
            simpleBooking.setHotelName(book.getHotel().getName());
            result.add(simpleBooking);
        }
        return result;
    }
}
