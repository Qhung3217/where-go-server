package com.wherego.wheregoserver.dto.traveler;

import com.wherego.wheregoserver.repository.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TravelerDto {
    private String email;
    private String name;
    private String tels;
    private String avatar;
    private Date dob;
    private String username;
    private Set<HotelReview> hotelReviews;
    private Set<PlaceReview> placeReviews;
    private Set<RestaurantReview> restaurantReviews;
    private Set<Booking> bookings;
}
