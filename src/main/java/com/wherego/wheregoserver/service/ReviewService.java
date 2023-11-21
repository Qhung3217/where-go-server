package com.wherego.wheregoserver.service;

import com.wherego.wheregoserver.dto.ResponseMessageDto;
import com.wherego.wheregoserver.dto.review.ReviewCreateDto;
import org.springframework.stereotype.Service;

@Service
public interface ReviewService {
    ResponseMessageDto reviewHotel(String token, ReviewCreateDto review);

    ResponseMessageDto reviewRestaurant(String token, ReviewCreateDto review);
}
