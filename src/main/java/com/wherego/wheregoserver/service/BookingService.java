package com.wherego.wheregoserver.service;

import com.wherego.wheregoserver.dto.ResponseMessageDto;
import com.wherego.wheregoserver.dto.booking.CreateBookingDto;
import org.springframework.stereotype.Service;

@Service

public interface BookingService {
    ResponseMessageDto create(String token, CreateBookingDto booking);
}
