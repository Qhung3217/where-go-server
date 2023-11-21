package com.wherego.wheregoserver.service.impl;

import com.wherego.wheregoserver.dto.ResponseMessageDto;
import com.wherego.wheregoserver.dto.review.ReviewCreateDto;
import com.wherego.wheregoserver.exception.InvalidFieldValueException;
import com.wherego.wheregoserver.exception.UserNotFoundException;
import com.wherego.wheregoserver.mapper.ReviewMapper;
import com.wherego.wheregoserver.mapper.TravelerMapper;
import com.wherego.wheregoserver.repository.HotelRepository;
import com.wherego.wheregoserver.repository.ReviewRepository;
import com.wherego.wheregoserver.repository.TravelerRepository;
import com.wherego.wheregoserver.repository.entity.Hotel;
import com.wherego.wheregoserver.repository.entity.HotelReview;
import com.wherego.wheregoserver.repository.entity.Traveler;
import com.wherego.wheregoserver.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private TravelerRepository travelerRepository;
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private ReviewMapper reviewMapper;
    @Autowired
    private JwtService jwtService;
    @Override
    public ResponseMessageDto reviewHotel(String token, ReviewCreateDto review) {
        try {
            if (review.getRating() < 1 || review.getRating() > 5)
                throw new InvalidFieldValueException("Rating must be between 1 and 5",
                        new String[]{"rating"});

            Traveler traveler = travelerRepository.getByEmail(jwtService.extractUsername(token));
            Hotel hotel = hotelRepository.getById(review.getCategoryId());

            HotelReview hotelReview = reviewMapper.toHotelReview(review);
            hotelReview.setTraveler(traveler);
            hotelReview.setHotel(hotel);

            reviewRepository.createHotelReview(hotelReview);
            return ResponseMessageDto
                    .builder()
                    .message("Create hotel review successfully")
                    .status(HttpStatus.CREATED)
                    .build();
        } catch (UserNotFoundException e) {
            throw new BadCredentialsException("Invalid token. User not found");
        }

    }
}
