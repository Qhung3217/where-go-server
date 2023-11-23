package com.wherego.wheregoserver.service.impl;

import com.wherego.wheregoserver.dto.ResponseMessageDto;
import com.wherego.wheregoserver.dto.booking.CreateBookingDto;
import com.wherego.wheregoserver.exception.InvalidFieldValueException;
import com.wherego.wheregoserver.mapper.BookingMapper;
import com.wherego.wheregoserver.repository.BookingRepository;
import com.wherego.wheregoserver.repository.HotelRepository;
import com.wherego.wheregoserver.repository.TravelerRepository;
import com.wherego.wheregoserver.repository.entity.Booking;
import com.wherego.wheregoserver.repository.entity.Hotel;
import com.wherego.wheregoserver.repository.entity.Traveler;
import com.wherego.wheregoserver.service.BookingService;
import com.wherego.wheregoserver.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private TravelerRepository travelerRepository;
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private BookingMapper bookingMapper;

    @Override
    public ResponseMessageDto create(String token, CreateBookingDto booking) {
        try {
            Date currentDate = new Date(System.currentTimeMillis());
            if (!validDate(currentDate,booking.getCheckIn(),booking.getCheckOut()))
                throw new InvalidFieldValueException("Check-in date must be equal or greater than" +
                        " current date and check-out date must be after check-in date");

            Traveler traveler = travelerRepository.getByEmail(jwtService.extractUsername(token));

            Hotel hotel = hotelRepository.getById(booking.getHotelId());

            Booking bookingEntity = bookingMapper.toBooking(booking);
            bookingEntity.setBookDate(currentDate);
            bookingEntity.setHotel(hotel);
            bookingEntity.setTraveler(traveler);

            bookingRepository.create(bookingEntity);

            return ResponseMessageDto
                    .builder()
                    .message("Create booking successfully")
                    .status(HttpStatus.CREATED)
                    .build();
        } catch (ParseException e) {
            return ResponseMessageDto
                    .builder()
                    .message("Incorrect date format")
                    .status(HttpStatus.PRECONDITION_FAILED)
                    .build();
        } catch (Exception e) {
            if (e.getMessage() == null) {
                return ResponseMessageDto
                        .builder()
                        .message("Not enough required fields")
                        .status(HttpStatus.BAD_REQUEST)
                        .build();
            } else {
                return ResponseMessageDto
                        .builder()
                        .message(e.getMessage())
                        .status(HttpStatus.CONFLICT)
                        .build();
            }
        }
    }

    private boolean validDate(Date current, Date checkIn, Date checkOut) {
        if (current.compareTo(checkIn) <= 0){
            return checkIn.before(checkOut);
        }

        return false;
    }
}
