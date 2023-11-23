package com.wherego.wheregoserver.dto.traveler;

import com.wherego.wheregoserver.dto.booking.SimpleBookingDto;
import com.wherego.wheregoserver.dto.review.SimpleReviewDto;
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
public class DetailTravelerDto {
    private String email;
    private String name;
    private String tels;
    private String avatar;
    private Date dob;
    private String username;
    private Set<SimpleReviewDto> hotelReviews;
    private Set<SimpleReviewDto> placeReviews;
    private Set<SimpleReviewDto> restaurantReviews;
    private Set<SimpleBookingDto> bookings;
}
