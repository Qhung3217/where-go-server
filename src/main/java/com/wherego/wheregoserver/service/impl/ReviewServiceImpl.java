package com.wherego.wheregoserver.service.impl;

import com.wherego.wheregoserver.dto.ResponseMessageDto;
import com.wherego.wheregoserver.dto.review.ReviewCreateDto;
import com.wherego.wheregoserver.exception.InvalidFieldValueException;
import com.wherego.wheregoserver.exception.UserNotFoundException;
import com.wherego.wheregoserver.mapper.ReviewMapper;
import com.wherego.wheregoserver.repository.HotelRepository;
import com.wherego.wheregoserver.repository.RestaurantRepository;
import com.wherego.wheregoserver.repository.ReviewRepository;
import com.wherego.wheregoserver.repository.TravelerRepository;
import com.wherego.wheregoserver.repository.entity.*;
import com.wherego.wheregoserver.service.JwtService;
import com.wherego.wheregoserver.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private TravelerRepository travelerRepository;
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private ReviewMapper reviewMapper;
    @Autowired
    private JwtService jwtService;

    @Override
    public ResponseMessageDto reviewHotel(String token, ReviewCreateDto review) {
        HotelReview hotelReview = reviewMapper.toHotelReview(review);
        return review(token, hotelReview, "hotel", review.getCategoryId());
    }

    @Override
    public ResponseMessageDto reviewRestaurant(String token, ReviewCreateDto review) {
        RestaurantReview restaurantReview = reviewMapper.toRestaurantReview(review);
        return review(token, restaurantReview, "restaurant", review.getCategoryId());
    }

    private ResponseMessageDto review(
            String token,
            Object objectReview,
            String category,
            Long categoryId
    ) {
        try {
            int rating = Integer.parseInt(
                    objectReview.getClass()
                            .getMethod("getRating")
                            .invoke(objectReview).toString()
            );
            if (rating < 1 || rating > 5)
                throw new InvalidFieldValueException("Rating must be between 1 and 5",
                        new String[]{"rating"});

            Traveler traveler = null;

            switch (category) {
                case "hotel":
                    traveler = travelerRepository.getByEmail(jwtService.extractUsername(token));
                    createReviewHotel(traveler, (HotelReview) objectReview, categoryId);
                    return ResponseMessageDto
                            .builder()
                            .message("Create hotel review successfully")
                            .status(HttpStatus.CREATED)
                            .build();

                case "restaurant":
                    traveler = travelerRepository.getByEmail(jwtService.extractUsername(token));
                    createReviewRestaurant(traveler, (RestaurantReview) objectReview, categoryId);
                    return ResponseMessageDto
                            .builder()
                            .message("Create restaurant review successfully")
                            .status(HttpStatus.CREATED)
                            .build();
                default:
                    throw new InvalidFieldValueException("Invalid category: " + category);
            }
        } catch (UserNotFoundException e) {
            throw new BadCredentialsException("Invalid token. User not found");
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private void createReviewHotel(
            Traveler traveler,
            HotelReview review,
            Long hotelId
    ) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Hotel hotel = hotelRepository.getById(hotelId);
        review.setHotel(hotel);

        review.setTraveler(traveler);

        reviewRepository.createHotelReview(review);
    }

    private void createReviewRestaurant(
            Traveler traveler,
            RestaurantReview review,
            Long restaurantId
    ) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Restaurant restaurant = restaurantRepository.getById(restaurantId);
        review.setRestaurant(restaurant);

        review.setTraveler(traveler);

        reviewRepository.createRestaurantReview(review);
    }


}
